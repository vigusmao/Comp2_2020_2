import java.util.ArrayList;
import java.util.List;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();

    private List<Produto> estoque;
    private List<Usuario> usuarios;

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    protected Loja() {
        // escrevo código normalmente para o construtor
        this.estoque = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    public void limparEstado() {
        // recria os atributos, ou os limpa...
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        Produto produtoEmEstoque = obterProdutoEmEstoque(produto);
        if (produtoEmEstoque != null) {
            produtoEmEstoque.setQuantidadeEmEstoque(
                    produtoEmEstoque.getQuantidadeEmEstoque() + quantidadeAIncluir);
        } else {
            this.estoque.add(produto);
            produto.setQuantidadeEmEstoque(quantidadeAIncluir);
        }
    }

    private Produto obterProdutoEmEstoque(Produto produto) {
//        for (Produto produtoEmEstoque : this.estoque) {
//            if (produtoEmEstoque.equals(produto)) {
//                return produtoEmEstoque;
//            }
//        }
//        return null;

        int indice = this.estoque.indexOf(produto);
        return indice != -1 ? this.estoque.get(indice) : null;

    }

    public void cadastrarUsuario(Usuario usuario) {
        Usuario usuarioJaCadastrado = obterUsuario(usuario.getCpf());
        if (usuarioJaCadastrado != null) {
            // nada a fazer, pois o usuário já está cadastrado
            return;
        }
        this.usuarios.add(usuario);
    }

    private Usuario obterUsuario(Usuario usuario) {
        int indice = this.usuarios.indexOf(usuario);
        return indice != -1 ? this.usuarios.get(indice) : null;
    }

    private Usuario obterUsuario(long cpf) {
        for (Usuario usuarioJaCadastrado : this.usuarios) {
            if (usuarioJaCadastrado.getCpf() == cpf) {
                return usuarioJaCadastrado;
            }
        }
        return null;
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {

        // conheço o usuário?
        if (obterUsuario(usuario) == null) {  // não conheço!
            return null;  // não efetua a venda
        }

        // existe o produto?
        Produto produtoEmEstoque = obterProdutoEmEstoque(produto);
        if (produtoEmEstoque == null ||
                produtoEmEstoque.getQuantidadeEmEstoque() < quantidadeDesejada) {
            return null;  // não efetua a venda
        }

        // vamos efetuar a venda
        produtoEmEstoque.setQuantidadeEmEstoque(
                produtoEmEstoque.getQuantidadeEmEstoque() - quantidadeDesejada);

        Recibo recibo = new Recibo(quantidadeDesejada * produto.getPrecoEmReais(),
                usuario, produto, quantidadeDesejada);
        return recibo;

    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        Produto produtoEmEstoque = obterProdutoEmEstoque(produto);
        if (produtoEmEstoque != null) {
            return produtoEmEstoque.getQuantidadeEmEstoque();
        }
        return -1;
    }
}
