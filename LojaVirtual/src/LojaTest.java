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

    private Loja loja;
    private Usuario comprador;
    private Livro guinessBook;
    private Brinquedo cuboMagico;

    @Before
    public void setUp() {
//        loja = new Loja();  // quebraria, porque implementamos o padrão singleton (c/ construtor privado)

        loja = Loja.getInstanciaUnica();
        comprador = new Usuario("Fulano", 123456, "Rua XYZ, 100");
        loja.cadastrarUsuario(comprador);

        guinessBook = new Livro("Guiness Book Of Records", "Editora Abril");
        guinessBook.setPrecoEmReais(50);

        cuboMagico = new Brinquedo("Cubo Mágico");
        cuboMagico.setPrecoEmReais(12.50f);
    }

    @Test
    public void testarInclusaoDeProdutoNoEstoque() {
        loja.incluirProduto(guinessBook, 100);
        assertEquals("O estoque deve ser atualizado após cada venda",
                100, loja.informarQuantidadeEmEstoque(guinessBook));

        loja.incluirProduto(guinessBook, 6);
        assertEquals("O estoque deve ser atualizado após cada venda",
                106, loja.informarQuantidadeEmEstoque(guinessBook));
    }
    @Test
    public void testarVendaParaUsuarioNaoCadastrado() {
        loja.incluirProduto(guinessBook, 100);
        Usuario compradorDesconhecido = new Usuario("Fantasma", 222, "Blah");
        assertNull("Apenas usuários cadastrados na loja podem comprar nela",
                loja.efetuarVenda(guinessBook, 5, compradorDesconhecido));
    }

    @Test
    public void testarVendaBemSucedida() {
        loja.incluirProduto(guinessBook, 100);

        Recibo recibo = loja.efetuarVenda(guinessBook, 5, comprador);
        assertNotNull(recibo);
        assertEquals(comprador, recibo.getUsuario());
        assertEquals(250, recibo.getValorTotalDaCompra(), FLOAT_DELTA);
        assertEquals("Recibo no valor de R$250" + sep + "00 para Fulano " +
                        "referente à compra de 5 unidades de Livro: Guiness Book Of Records",
                recibo.toString());

        assertEquals("O estoque deve ser atualizado após cada venda",
                95, loja.informarQuantidadeEmEstoque(guinessBook));
    }

    @Test
    public void testarVendaParaProdutoEmQuantidadeInsuficiente() {
        loja.incluirProduto(guinessBook, 100);
        assertNull("A venda não deve ser efetuada se não houver quantidade suficiente",
                loja.efetuarVenda(guinessBook, 101, comprador));
    }

    @Test
    public void testarVendaParaProdutoQueNaoEhVendidoPelaLoja() {
        assertNull("A venda não deve ser efetuada se a loja não trabalhar com aquele produto",
                loja.efetuarVenda(cuboMagico, 1, comprador));
    }

    @Test
    public void testarLojaVendendoProdutosDiversos() {
        loja.incluirProduto(guinessBook, 1);
        loja.incluirProduto(cuboMagico, 5);
        // não é preciso asserts, apenas está aqui para ver que isso precisa compilar corretamente
    }
}