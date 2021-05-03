import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementa uma loja virtual para vendavels de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    static final float PRECO_DEFAULT = 1.99f;

    private Map<Vendavel, InfoVendavel> infoVendaveis;

    private Map<Long, Usuario> usuarioByCpf;  // Map<Chave, Valor> valorByChave

    private Transportador entregador;


    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    public Loja(Transportador entregador) {
        this.infoVendaveis = new HashMap<>();  // composição
        this.usuarioByCpf = new HashMap<>();   // composição
        this.entregador = entregador;  // agregação
    }

    /**
     * Inclui no vendavels da loja a quantidade informado do vendavel.
     *
     * @param vendavel o vendavel a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirVendavel(Vendavel vendavel, int quantidadeAIncluir) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);

        if (infoVendavel != null) {
            // vendavel já existe no estoque da loja
            infoVendavel.quantidade += quantidadeAIncluir;
//  se precisássemos, por alguma razão, criar outro OBJETO, aí sim precisaríamos
//  atualizar o mapa, fazendo com que aquela chave apontasse para o novo objeto
//
//            infoVendavel = new InfoVendavel(vendavel,
//                    infoVendavel.quantidade + quantidadeAIncluir, PRECO_DEFAULT);
//            this.infoVendaveis.put(vendavel, infoVendavel);
//            this.infoVendaveis.add(infoVendavel);

        } else {
            // vendavel não existe ainda, vamos incluir
            infoVendavel = new InfoVendavel(vendavel, quantidadeAIncluir, PRECO_DEFAULT);
            this.infoVendaveis.put(vendavel, infoVendavel);
        }
    }

    public void atribuirPreco(Vendavel vendavel, float novoPreco) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        if (infoVendavel != null) {
            infoVendavel.preco = novoPreco;
        }
    }

    private InfoVendavel obterInfoVendavel(Vendavel vendavelASerConsultado) {
        return this.infoVendaveis.get(vendavelASerConsultado);
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
     * Efetua a venda do vendavel desejado na quantidade especificada.
     *
     * @param vendavel o vendavel
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o vendavel existia (em quantidade suficiente)
     *         no vendavels da loja; null, caso o usuário ou o vendavel sejam desconhecidos,
     *         ou não haja quantidade suficiente do vendavel desejado
     */
    public Recibo efetuarVenda(
            Vendavel vendavel, int quantidadeDesejada, Usuario usuario) {

        // a quantidade desejada é positiva?
        if (quantidadeDesejada < 1) {
            return null;
        }

        // conheço o usuário?
        if (obterUsuario(usuario) == null) {  // não conheço!
            return null;  // não efetua a venda
        }

        // existe o vendavel?
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        if (infoVendavel == null) {
            return null;  // não efetua a venda
        }
        if (infoVendavel.quantidade < quantidadeDesejada) {
            return null;  // não efetua a venda
        }

        // vamos efetuar a venda
        infoVendavel.quantidade -= quantidadeDesejada;

        // solicita a entrega
        if (vendavel instanceof Produto) {
            this.entregador.transportar((Produto) vendavel, usuario.getEndereco());
        }

        Recibo recibo = new Recibo(quantidadeDesejada * infoVendavel.preco,
                usuario, vendavel, quantidadeDesejada);
        return recibo;
    }

    /**
     * @param vendavel o vendavel a ser consultado
     *
     * @return a quantidade em vendavels;
     *         0 se não houver nenhuma unidade;
     *         -1 se o vendavel não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Vendavel vendavel) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        return infoVendavel != null ? infoVendavel.quantidade : -1;
    }

    public void listarTodosOsItens() {
        for (InfoVendavel infoVendavel : this.infoVendaveis.values()) {
            Vendavel vendavel = infoVendavel.vendavel;
            String descricao = vendavel.getDescricao();
            Image imagem = obterImagemDaRede(vendavel.getUrlDaImagem());
            float preco = infoVendavel.preco;
            // ToDo exibir a descrição do item com sua imagem e seu preço nesta loja
        }
    }

    private Image obterImagemDaRede(String url) {
        // ToDo...
        return null;
    }

    private class InfoVendavel {
        Vendavel vendavel;
        float preco;
        int quantidade;
        int vendasEfetuadas;
        int avaliacaoDoVendavel;

        InfoVendavel(Vendavel vendavel, int quantidade, float preco) {
            this.vendavel = vendavel;
            this.preco = preco;
            this.quantidade = quantidade;
        }
    }
}
