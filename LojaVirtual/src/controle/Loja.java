package controle;

import exception.EstoqueInsuficienteException;
import exception.ItemInexisteNoCatalogoException;
import modelo.Usuario;
import modelo.produto.Produto;
import util.Transportador;
import util.Vendavel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementa uma loja virtual para qualquer tipo de util.Vendavel,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja<T extends Vendavel> {  // SEMPRE extends

    public static final float PRECO_DEFAULT = 1.99f;

    private Map<T, InfoVendavel> infoByVendavel;

    private Map<Long, Usuario> usuarioByCpf;  // Map<Chave, Valor> valorByChave

    private Set<T> produtosEmPromocao;

    private Transportador entregador;


    static {
        System.out.println("Estou subindo a classe controle.Loja...");
    }

    public Loja(Transportador entregador) {
        this.infoByVendavel = new HashMap<>();  // composição
        this.usuarioByCpf = new HashMap<>();   // composição
        this.entregador = entregador;  // agregação
    }

    /**
     * Inclui no vendavels da loja a quantidade informado do vendavel.
     *
     * @param vendavel o vendavel a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirVendavel(T vendavel, int quantidadeAIncluir) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);

        if (infoVendavel != null) {
            // vendavel já existe no estoque da loja
            infoVendavel.quantidade += quantidadeAIncluir;
//  se precisássemos, por alguma razão, criar outro OBJETO, aí sim precisaríamos
//  atualizar o mapa, fazendo com que aquela chave apontasse para o novo objeto
//
//            infoVendavel = new InfoVendavel(vendavel,
//                    infoVendavel.quantidade + quantidadeAIncluir, PRECO_DEFAULT);
//            this.infoByVendavel.put(vendavel, infoVendavel);
//            this.infoByVendavel.add(infoVendavel);

        } else {
            // vendavel não existe ainda, vamos incluir
            infoVendavel = new InfoVendavel(vendavel, quantidadeAIncluir, PRECO_DEFAULT);
            this.infoByVendavel.put(vendavel, infoVendavel);
        }
    }

    public void atribuirPreco(T vendavel, float novoPreco) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        if (infoVendavel != null) {
            infoVendavel.preco = novoPreco;
        }
    }

    private InfoVendavel obterInfoVendavel(T vendavelASerConsultado) {
        return this.infoByVendavel.get(vendavelASerConsultado);
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
     * @return um controle.Recibo indicando a venda feita, se o vendavel existia (em quantidade suficiente)
     *         no vendavels da loja; null, caso o usuário seja desconhecido
     *
     * @throws EstoqueInsuficienteException se não houver o iteem quantidade suficiente para a venda
     * @throws ItemInexisteNoCatalogoException se a loja sequer trabalhar com esse item
     */
    public Recibo efetuarVenda(
            T vendavel, int quantidadeDesejada, Usuario usuario) throws EstoqueInsuficienteException,
                                                                        ItemInexisteNoCatalogoException {

        // a quantidade desejada é positiva?
        if (quantidadeDesejada < 1) {
            throw new RuntimeException("Argumento inválido! Quantidade desejada precisa ser positiva!");
            // situação onde há claramente um bug no chamador!!!!!!!!
        }

        // conheço o usuário?
        if (obterUsuario(usuario) == null) {  // não conheço!
            return null;  // não efetua a venda e retorna null (não é legal!!!!)
        }

        // existe o vendavel?
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        if (infoVendavel == null) {
            throw new ItemInexisteNoCatalogoException();  // não efetua a venda
        }
        if (infoVendavel.quantidade < quantidadeDesejada) {
            throw new EstoqueInsuficienteException();
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
    public int informarQuantidadeEmEstoque(T vendavel) {
        InfoVendavel infoVendavel = obterInfoVendavel(vendavel);
        return infoVendavel != null ? infoVendavel.quantidade : -1;
    }

    public void listarTodosOsItens() {
        for (InfoVendavel infoVendavel : this.infoByVendavel.values()) {
            T vendavel = infoVendavel.vendavel;
            String descricao = vendavel.getDescricao();
            Image imagem = obterImagemDaRede(vendavel.getUrlDaImagem());
            float preco = infoVendavel.preco;
            // ToDo exibir a descrição do item com sua imagem e seu preço nesta loja
        }
    }

    public T obterItem(String descricao) {
        for (T vendavel : this.infoByVendavel.keySet()) {
            if (vendavel.getDescricao() != null &&
                vendavel.getDescricao().equals(descricao)) {
                return vendavel;
            }
        }
        return null;
    }


    private Image obterImagemDaRede(String url) {
        // ToDo...
        return null;
    }

    private class InfoVendavel {
        T vendavel;
        float preco;
        int quantidade;
        int vendasEfetuadas;
        int avaliacaoDoVendavel;

        InfoVendavel(T vendavel, int quantidade, float preco) {
            this.vendavel = vendavel;
            this.preco = preco;
            this.quantidade = quantidade;
        }
    }
}
