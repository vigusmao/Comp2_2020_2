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
        guinessBook.setPrecoEmReais(50);

        cuboMagico = new Brinquedo("Cubo Mágico");
        cuboMagico.setPrecoEmReais(12.50f);
    }

    @Test
    public void testarInclusaoDeProdutoNoEstoque() {
        loja1.incluirProduto(guinessBook, 100);
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

        Recibo recibo = loja1.efetuarVenda(guinessBook, 5, comprador);
        assertNotNull(recibo);
        assertEquals(comprador, recibo.getUsuario());
        assertEquals(250, recibo.getValorTotalDaCompra(), FLOAT_DELTA);
        assertEquals("Recibo no valor de R$250" + sep + "00 para Fulano " +
                        "referente à compra de 5 unidades de Livro: Guiness Book Of Records",
                recibo.toString());

        assertEquals("O estoque deve ser atualizado após cada venda",
                95, loja1.informarQuantidadeEmEstoque(guinessBook));
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
}