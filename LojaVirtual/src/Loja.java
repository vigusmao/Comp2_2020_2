import java.util.HashMap;
import java.util.Map;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final float PRECO_DEFAULT = 1.99f;

    private Map<Produto, InfoProduto> infoProdutos;

    private Map<Long, Usuario> usuarioByCpf;  // Map<Chave, Valor> valorByChave

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    public Loja() {
        this.infoProdutos = new HashMap<>();
        this.usuarioByCpf = new HashMap<>();
    }

    /**
     * Inclui no produtos da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        InfoProduto infoProduto = obterInfoProduto(produto);

        if (infoProduto != null) {
            // produto já existe no estoque da loja
//            infoProduto.quantidade += quantidadeAIncluir;
//  se precisássemos, por alguma razão, criar outro OBJETO, aí sim precisaríamos
//  atualizar o mapa, fazendo com que aquela chave apontasse para o novo objeto
//
            infoProduto = new InfoProduto(produto,
                    infoProduto.quantidade + quantidadeAIncluir, PRECO_DEFAULT);
            this.infoProdutos.put(produto, infoProduto);

        } else {
            // produto não existe ainda, vamos incluir
            infoProduto = new InfoProduto(produto, quantidadeAIncluir, PRECO_DEFAULT);
            this.infoProdutos.put(produto, infoProduto);
        }
    }

    public void atribuirPreco(Produto produto, float novoPreco) {
        InfoProduto infoProduto = obterInfoProduto(produto);
        if (infoProduto != null) {
            infoProduto.preco = novoPreco;
        }
    }

    private InfoProduto obterInfoProduto(Produto produtoASerConsultado) {
        return this.infoProdutos.get(produtoASerConsultado);
    }

    public void cadastrarUsuario(Usuario usuario) {
        final long cpf = usuario.getCpf();
        Usuario usuarioJaCadastrado = obterUsuario(cpf);
        if (usuarioJaCadastrado != null) {
            // nada a fazer, pois o usuário já está cadastrado
            return;
        }
        this.usuarioByCpf.put(cpf, usuario);
    }

    private Usuario obterUsuario(Usuario usuario) {
        return obterUsuario(usuario.getCpf());
    }

    private Usuario obterUsuario(long cpf) {
        return this.usuarioByCpf.get(cpf);
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
        InfoProduto infoProduto = obterInfoProduto(produto);
        if (infoProduto == null) {
            return null;  // não efetua a venda
        }
        if (infoProduto.quantidade < quantidadeDesejada) {
            return null;  // não efetua a venda
        }

        // vamos efetuar a venda
        infoProduto.quantidade -= quantidadeDesejada;

        Recibo recibo = new Recibo(quantidadeDesejada * infoProduto.preco,
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
        InfoProduto infoProduto = obterInfoProduto(produto);
        return infoProduto != null ? infoProduto.quantidade : -1;
    }

    private class InfoProduto {
        Produto produto;
        float preco;
        int quantidade;
        int vendasEfetuadas;
        int avaliacaoDoProduto;

        InfoProduto(Produto produto, int quantidade, float preco) {
            this.produto = produto;
            this.preco = preco;
            this.quantidade = quantidade;
        }
    }
}
