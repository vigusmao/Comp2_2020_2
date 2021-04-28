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

    private static final float PRECO_DEFAULT = 1.99f;

    private List<Produto> produtos;
    private List<Integer> quantidades;
    private List<Float> precos;

    private List<Usuario> usuarios;

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    public Loja() {
        this.produtos = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.precos = new ArrayList<>();

        this.usuarios = new ArrayList<>();
    }

    /**
     * Inclui no produtos da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        int indice = obterIndiceProdutoEmEstoque(produto);

        if (indice != -1) {  // produto já existe no estoque da loja
            int novoValor = this.quantidades.get(indice) + quantidadeAIncluir;
//            this.quantidades[indice] = novoValor;  // se fosse array
            this.quantidades.set(indice, novoValor);

        } else {
            this.produtos.add(produto);
            this.quantidades.add(quantidadeAIncluir);
            this.precos.add(PRECO_DEFAULT);
        }
    }

    public void atribuirPreco(Produto produto, float novoPreco) {
        int indice = obterIndiceProdutoEmEstoque(produto);

        if (indice == -1) {
            return;  // não vou atribuir preço algum -- o produto não existe!
        }

        this.precos.set(indice, novoPreco);
    }

    private int obterIndiceProdutoEmEstoque(Produto produto) {
        return this.produtos.indexOf(produto);  // pra funcionar, preciso do override no equals()
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
     *         no produtos da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {

        // a quantidade desejada é positiva?
        if (quantidadeDesejada < 1) {
            return null;
        }

        // conheço o usuário?
        if (obterUsuario(usuario) == null) {  // não conheço!
            return null;  // não efetua a venda
        }

        // existe o produto?
        int indiceProdutoEmEstoque = obterIndiceProdutoEmEstoque(produto);
        if (indiceProdutoEmEstoque == -1) {
            return null;  // não efetua a venda
        }
        int quantidadeEmEstoque = this.quantidades.get(indiceProdutoEmEstoque);
        if (quantidadeEmEstoque < quantidadeDesejada) {
            return null;  // não efetua a venda
        }

        // vamos efetuar a venda
        int novaQuantidade = quantidadeEmEstoque - quantidadeDesejada;
        this.quantidades.set(indiceProdutoEmEstoque, novaQuantidade);

        Recibo recibo = new Recibo(quantidadeDesejada * this.precos.get(indiceProdutoEmEstoque),
                usuario, produto, quantidadeDesejada);
        return recibo;

    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em produtos;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        int indiceProdutoEmEstoque = obterIndiceProdutoEmEstoque(produto);
        return indiceProdutoEmEstoque != -1 ? this.quantidades.get(indiceProdutoEmEstoque) : -1;
    }
}
