import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Colecionavel> todasAsFigurinhas;

    private int quantFigurinhas;

    @SuppressWarnings("unchecked")
    public Repositorio(String sufixoUrlImagens, int quantFigurinhas, String tipoDeColecionavel) {
        this.quantFigurinhas = quantFigurinhas;

        todasAsFigurinhas = new ArrayList<>();
        todasAsFigurinhas.add(null);  // ocupando a posição 0 com um null
        for (int i = 1; i <= quantFigurinhas; i++) {

            Colecionavel item = ColecionavelFactory.criarColecionavel(
                    tipoDeColecionavel, i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todasAsFigurinhas.add(item);
        }
    }

    public int getTotalFigurinhas() {
        return this.quantFigurinhas;
    }

    public Colecionavel getItem(int pos) {
        return this.todasAsFigurinhas.get(pos);
    }
}
