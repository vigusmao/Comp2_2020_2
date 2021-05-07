import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CalculadorIntersecaoEsperto implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        HashSet<Usuario> conjunto = new HashSet<>();

        List<Usuario> intersecao = new ArrayList<>();

        // para cada elemento da primeira lista, insira-o como chave no conjunto
        for (Usuario usuarioDaPrimeiraLista : lista1) {
            conjunto.add(usuarioDaPrimeiraLista);
        }

        // para cada elemento da segunda lista, verifica se é chave do conjunto
        for (Usuario usuarioDaSegundaLista : lista2) {
            if (conjunto.contains(usuarioDaSegundaLista)) {
                intersecao.add(usuarioDaSegundaLista);
            }
        }

        return intersecao;
    }
}
