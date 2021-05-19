package test;

import controle.Loja;
import controle.Recibo;
import modelo.Usuario;
import modelo.produto.Brinquedo;
import modelo.produto.Livro;
import modelo.produto.LivroOuBrinquedo;
import modelo.produto.Produto;
import modelo.servico.Servico;
import modelo.servico.ServicoEntregas;
import modelo.servico.ServicoImpressao;
import org.junit.Before;
import org.junit.Test;
import util.Caminhao;
import util.Vendavel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.junit.Assert.*;

public class LojaTest {

    private static final float FLOAT_DELTA = 0.0000001f;

    // captura do SO o separador decimal utilizado
    private static DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
    private static DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
    private static char sep = symbols.getDecimalSeparator();

    private Loja<Vendavel> loja1;
    private Loja<Vendavel> loja2;
    private Caminhao caminhao;
    private ServicoEntregas servicoEntregas;
    private Usuario comprador;
    private Livro guinessBook;
    private Brinquedo cuboMagico;

    @Before  // roda antes de cada teste...
    public void setUp() {
        caminhao = new Caminhao("Scania", "Jamanta", 2000);
        servicoEntregas = new ServicoEntregas();

        loja1 = new Loja<>(servicoEntregas);
        loja2 = new Loja<>(caminhao);

        comprador = new Usuario("Fulano", 123456, "Rua XYZ, 100");
        loja1.cadastrarUsuario(comprador);
        loja2.cadastrarUsuario(comprador);

        guinessBook = new Livro("Guiness Book Of Records", "Editora Abril");
        cuboMagico = new Brinquedo("Cubo Mágico");
    }

    @Test
    public void testarInclusaoDeVendavelNoEstoque() {
        loja1.incluirVendavel(guinessBook, 100);
        loja1.atribuirPreco(guinessBook, 50);

        assertEquals("O estoque deve refletir a quantidade correta do vendavel",
                100, loja1.informarQuantidadeEmEstoque(guinessBook));

        loja1.incluirVendavel(guinessBook, 6);
        assertEquals("O estoque deve ser atualizado após cada inclusão",
                106, loja1.informarQuantidadeEmEstoque(guinessBook));

        // vamos cadastrar o mesmo vendavel na segunda loja
        loja2.incluirVendavel(guinessBook, 20);
        assertEquals("O estoque de cada loja deve ser independente",
                20, loja2.informarQuantidadeEmEstoque(guinessBook));

        // agora vamos consultar novamente a quantidade daquele vendavel na primeira loja
        assertEquals("O estoque deve ser atualizado após cada inclusão",
                106, loja1.informarQuantidadeEmEstoque(guinessBook));

        loja2.incluirVendavel(caminhao, 5);
    }

    @Test
    public void testarConsultaDeVendavelUsandoOutroObjeto() {
        loja1.incluirVendavel(guinessBook, 100);

        Livro outroObjetoRepresentandoOMesmoLivro = new Livro(
                guinessBook.getNome(), guinessBook.getEditora());

        assertEquals("O estoque deve ser atualizado após cada inclusão",
                100, loja1.informarQuantidadeEmEstoque(
                        outroObjetoRepresentandoOMesmoLivro));
    }

    @Test
    public void testarVendaParaUsuarioNaoCadastrado() {
        loja1.incluirVendavel(guinessBook, 100);
        Usuario compradorDesconhecido = new Usuario("Fantasma", 222, "Blah");
        assertNull("Apenas usuários cadastrados na loja1 podem comprar nela",
                loja1.efetuarVenda(guinessBook, 5, compradorDesconhecido));
    }

    @Test
    public void testarVendaBemSucedida() {
        loja1.incluirVendavel(guinessBook, 100);
        loja1.atribuirPreco(guinessBook, 50);

        loja2.incluirVendavel(guinessBook, 20);
        loja2.atribuirPreco(guinessBook, 45);

        ServicoImpressao servicoImpressao = new ServicoImpressao();
        loja2.incluirVendavel(servicoImpressao, 1);

        Servico servicoPinturaResidencial = new Servico();
        // loja2.incluirVendavel(servicoPinturaResidencial, 1);  // NÃO COMPILA!!!!

        verificarVendaBemSucedida(loja1, guinessBook, 5, 250, 95);
        verificarVendaBemSucedida(loja2, guinessBook, 3, 135, 17);
        verificarVendaBemSucedida(loja2, servicoImpressao, 1, Loja.PRECO_DEFAULT, 0);
    }

    private void verificarVendaBemSucedida(Loja loja, Vendavel vendavel, int quantidadeComprada,
                                           float valorEsperadoDaCompra, int quantidadeEsperadaNoEstoquePosVenda) {
        Recibo recibo = loja.efetuarVenda(vendavel, quantidadeComprada, comprador);
        assertNotNull(recibo);
        assertEquals(comprador, recibo.getUsuario());
        assertEquals(valorEsperadoDaCompra, recibo.getValorTotalDaCompra(), FLOAT_DELTA);
        assertEquals(String.format("controle.Recibo no valor de R$%.2f para Fulano " +
                        "referente à compra de %d unidades de %s",
                valorEsperadoDaCompra, quantidadeComprada, vendavel.getDescricao()),
                recibo.toString());
        assertEquals("O estoque deve ser atualizado após cada venda",
                quantidadeEsperadaNoEstoquePosVenda, loja.informarQuantidadeEmEstoque(vendavel));
    }

    @Test
    public void testarVendaComQuantidadeInformadaNegativa() {
        loja1.incluirVendavel(guinessBook, 100);

        Recibo recibo = loja1.efetuarVenda(guinessBook, -1, comprador);
        assertNull(recibo);
        assertEquals("O estoque não deve ser alterado após venda inválida",
                100, loja1.informarQuantidadeEmEstoque(guinessBook));
    }

    @Test
    public void testarVendaParaVendavelEmQuantidadeInsuficiente() {
        loja1.incluirVendavel(guinessBook, 100);
        assertNull("A venda não deve ser efetuada se não houver quantidade suficiente",
                loja1.efetuarVenda(guinessBook, 101, comprador));
    }

    @Test
    public void testarVendaParaVendavelQueNaoEhVendidoPelaLoja() {
        assertNull("A venda não deve ser efetuada se a loja1 não trabalhar com aquele vendavel",
                loja1.efetuarVenda(cuboMagico, 1, comprador));
    }

    @Test
    public void testarLojaVendendoVendavelsDiversos() {
        loja1.incluirVendavel(guinessBook, 1);
        loja1.incluirVendavel(cuboMagico, 5);
        // não é preciso asserts, apenas está aqui para ver que isso precisa compilar corretamente
    }

    @Test
    public void testarPerformance() {
        int QUANT_VENDAVELS = 100_000;

        System.out.println("Incluindo " + QUANT_VENDAVELS + " vendavels...");
        long inicio = System.currentTimeMillis();
        for (int i = 1; i <= QUANT_VENDAVELS; i++) {
            Produto novoVendavel = new Produto("modelo.produto.Produto " + i);
            loja1.incluirVendavel(novoVendavel, 2);
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\nDuração = %.3fs\n\n", duracao / 1000f);

        System.out.println("Buscando vendavels...");
        inicio = System.currentTimeMillis();
        int contTotalEstoque = 0;
        for (int i = 1; i <= QUANT_VENDAVELS; i++) {
            Produto produtoASerConsultado = new Produto("modelo.produto.Produto " + i);

            contTotalEstoque += loja1.informarQuantidadeEmEstoque(
                    produtoASerConsultado);
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\nDuração = %.3fs\n", duracao / 1000f);
        System.out.printf("\nTotal em estoque = %d", contTotalEstoque);
    }

    @Test
    public void testartLojaQueVendoAlgoEspecifico() {
        Loja<Livro> livraria = new Loja<>(caminhao);  // queremos que seja uma loja EXCLUSIVA para Livros

        // eu gostaria que as linhas abaixo SEQUER COMPILASSEM!!!!!!
//        livraria.incluirVendavel(cuboMagico, 50);
//        livraria.incluirVendavel(caminhao, 2);


        String nome = "O Pequeno Príncipe";
        String editora = "Editora Blah";
        Livro livro = new Livro(nome, editora);
        livro.setAnoPublicacao(1882);
        livraria.incluirVendavel(livro, 10);
        final String descricao = Livro.formatarDescricaoLivro(nome, editora);

        // eu quero que a linha abaixo COMPILE direitinho, sem necessidade de typecast
        Livro livroDaLoja = livraria.obterItem(descricao);
        assertEquals(1882, livroDaLoja.getAnoDePublicacao());

        Loja<Brinquedo> lojaDeBrinquedos = new Loja<>(caminhao);

        // eu gostaria que as linhas abaixo SEQUER COMPILASSEM!!!!!!
//        lojaDeBrinquedos.incluirVendavel(guinessBook, 2);
//        lojaDeBrinquedos.incluirVendavel(caminhao, 50);

            // eu gostaria que a linha abaixo COMPILASSE direitinho, sem necessidade de typecast
        Brinquedo brinquedoDaLoja = lojaDeBrinquedos.obterItem("Cubo Mágico");

        Loja<LivroOuBrinquedo> lojaDeLivroOuBrinquedo  = new Loja<>(caminhao);
        lojaDeLivroOuBrinquedo.incluirVendavel(guinessBook, 30);
        lojaDeLivroOuBrinquedo.incluirVendavel(cuboMagico, 10);

        Brinquedo brinquedo = (Brinquedo) lojaDeLivroOuBrinquedo.obterItem("Cubo Mágico");
    }
}