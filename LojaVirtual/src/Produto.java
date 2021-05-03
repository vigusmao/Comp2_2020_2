import java.awt.*;
import java.util.Objects;

public class Produto implements Vendavel, Transportavel {

    private String descricao;
    private int pesoEmGramas;
    private Dimensoes dimensoes;
    private String urlDaImagem;
    private Image codigoDeBarras;
    private int anoDeFabricacao;

    public Produto(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;
    }

    public int getPesoEmGramas() {
        return this.pesoEmGramas;
    }

    public Dimensoes getDimensoes() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public String getUrlDaImagem() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao);
    }
}
