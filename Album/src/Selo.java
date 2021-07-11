import java.awt.*;

public class Selo implements Colecionavel {

    private float valorNominal;
    private String pais;
    private int posicao;
    private Image imagem;

    public Selo(int posicao, String urlDaImagem, float valorNominal) {
        this.posicao = posicao;
        this.valorNominal = valorNominal;
        this.imagem = null;   // leria da rede pela url passada
    }

    @Override
    public Image getImagem() {
        return imagem;
    }

    @Override
    public int getPosicao() {
        return 0;
    }

    public float getValorNominal() {
        return 0;
    }

    public String getPais() {
        return null;
    }
}
