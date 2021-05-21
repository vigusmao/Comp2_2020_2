import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<T> todosOsItens;

    private int quantFigurinhas;

    @SuppressWarnings("unchecked")
    public Repositorio(String sufixoUrlImagens, int quantItens, String tipoDeColecionavel) {
        this.quantFigurinhas = quantItens;

        todosOsItens = new ArrayList<>();
        todosOsItens.add(null);  // ocupando a posição 0 com um null
        for (int i = 1; i <= quantItens; i++) {

            T item = (T) ColecionavelFactory.criarColecionavel(
                    tipoDeColecionavel, i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsItens.add(item);
        }
    }

    public int getTotalFigurinhas() {
        return this.quantFigurinhas;
    }

    public T getItem(int pos) {
        return this.todosOsItens.get(pos);
    }
}
