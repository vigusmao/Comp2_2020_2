import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.junit.Assert.*;

public class LojaTest {

    private static final float FLOAT_DELTA = 0.0000001f;

    // captura do SO o separador decimal utilizado
    private static DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
    private static DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
    private static char sep = symbols.getDecimalSeparator();

    private Loja loja1;
    private Loja loja2;
    private Usuario comprador;
    private Livro guinessBook;
    private Brinquedo cuboMagico;

    @Before  // roda antes de cada teste...
    public void setUp() {
        loja1 = new Loja();
        loja2 = new Loja();

        comprador = new Usuario("Fulano", 123456, "Rua XYZ, 100");
        loja1.cadastrarUsuario(comprador);
        loja2.cadastrarUsuario(comprador);

        guinessBook = new Livro("Guiness Book Of Records", "Editora Abril");
        cuboMagico = new Brinquedo("Cubo Mágico");
    }

    @Test
    public void testarInclusaoDeProdutoNoEstoque() {
        loja1.incluirProduto(guinessBook, 100);
        loja1.atribuirPreco(guinessBook, 50);

        assertEquals("O estoque deve refletir a quantidade correta do produto",
                100, loja1.informarQuantidadeEmEstoque(guinessBook));

        loja1.incluirProduto(guinessBook, 6);
        assertEquals("O estoque deve ser atualizado após cada inclusão",
                106, loja1.informarQuantidadeEmEstoque(guinessBook));

        // vamos cadastrar o mesmo produto na segunda loja
        loja2.incluirProduto(guinessBook, 20);
        assertEquals("O estoque de cada loja deve ser independente",
                20, loja2.informarQuantidadeEmEstoque(guinessBook));

        // agora vamos consultar novamente a quantidade daquele produto na primeira loja
        assertEquals("O estoque deve ser atualizado após cada inclusão",
                106, loja1.informarQuantidadeEmEstoque(guinessBook));
    }

    @Test
    public void testarConsultaDeProdutoUsandoOutroObjeto() {
        loja1.incluirProduto(guinessBook, 100);

        Livro outroObjetoRepresentandoOMesmoLivro = new Livro(
                guinessBook.getNome(), guinessBook.getEditora());

        assertEquals("O estoque deve ser atualizado após cada inclusão",
                100, loja1.informarQuantidadeEmEstoque(
                        outroObjetoRepresentandoOMesmoLivro));
    }

    @Test
    public void testarVendaParaUsuarioNaoCadastrado() {
        loja1.incluirProduto(guinessBook, 100);
        Usuario compradorDesconhecido = new Usuario("Fantasma", 222, "Blah");
        assertNull("Apenas usuários cadastrados na loja1 podem comprar nela",
                loja1.efetuarVenda(guinessBook, 5, compradorDesconhecido));
    }

    @Test
    public void testarVendaBemSucedida() {
        loja1.incluirProduto(guinessBook, 100);
        loja1.atribuirPreco(guinessBook, 50);

        loja2.incluirProduto(guinessBook, 20);
        loja2.atribuirPreco(guinessBook, 45);

        verificarVendaBemSucedida(loja1, guinessBook, 5, 250, 95);
        verificarVendaBemSucedida(loja2, guinessBook, 3, 135, 17);
    }

    private void verificarVendaBemSucedida(Loja loja, Produto produto, int quantidadeComprada,
                                           int valorEsperadoDaCompra, int quantidadeEsperadaNoEstoquePosVenda) {
        Recibo recibo = loja.efetuarVenda(produto, quantidadeComprada, comprador);
        assertNotNull(recibo);
        assertEquals(comprador, recibo.getUsuario());
        assertEquals(valorEsperadoDaCompra, recibo.getValorTotalDaCompra(), FLOAT_DELTA);
        assertEquals("Recibo no valor de R$" + valorEsperadoDaCompra + sep + "00 para Fulano " +
                        "referente à compra de " + quantidadeComprada + " unidades de " + produto.toString(),
                recibo.toString());
        assertEquals("O estoque deve ser atualizado após cada venda",
                quantidadeEsperadaNoEstoquePosVenda, loja.informarQuantidadeEmEstoque(produto));
    }

    @Test
    public void testarVendaComQuantidadeInformadaNegativa() {
        loja1.incluirProduto(guinessBook, 100);

        Recibo recibo = loja1.efetuarVenda(guinessBook, -1, comprador);
        assertNull(recibo);
        assertEquals("O estoque não deve ser alterado após venda inválida",
                100, loja1.informarQuantidadeEmEstoque(guinessBook));
    }

    @Test
    public void testarVendaParaProdutoEmQuantidadeInsuficiente() {
        loja1.incluirProduto(guinessBook, 100);
        assertNull("A venda não deve ser efetuada se não houver quantidade suficiente",
                loja1.efetuarVenda(guinessBook, 101, comprador));
    }

    @Test
    public void testarVendaParaProdutoQueNaoEhVendidoPelaLoja() {
        assertNull("A venda não deve ser efetuada se a loja1 não trabalhar com aquele produto",
                loja1.efetuarVenda(cuboMagico, 1, comprador));
    }

    @Test
    public void testarLojaVendendoProdutosDiversos() {
        loja1.incluirProduto(guinessBook, 1);
        loja1.incluirProduto(cuboMagico, 5);
        // não é preciso asserts, apenas está aqui para ver que isso precisa compilar corretamente
    }

    @Test
    public void testarPerformance() {
        int QUANT_PRODUTOS = 100_000;

        System.out.println("Incluindo " + QUANT_PRODUTOS + " produtos...");
        long inicio = System.currentTimeMillis();
        for (int i = 1; i <= QUANT_PRODUTOS; i++) {
            Produto novoProduto = new Produto("Produto " + i);
            loja1.incluirProduto(novoProduto, 2);
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\nDuração = %.3fs\n\n", duracao / 1000f);

        System.out.println("Buscando produtos...");
        inicio = System.currentTimeMillis();
        int contTotalEstoque = 0;
        for (int i = 1; i <= QUANT_PRODUTOS; i++) {
            Produto produtoASerConsultado = new Produto("Produto " + i);

            contTotalEstoque += loja1.informarQuantidadeEmEstoque(
                    produtoASerConsultado);
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("\nDuração = %.3fs\n", duracao / 1000f);
        System.out.printf("\nTotal em estoque = %d", contTotalEstoque);
    }
}