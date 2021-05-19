import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        List<Usuario> intersecao = new ArrayList<>();

        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
        for (Usuario usuario1 : lista1) {
            if (lista2.contains(usuario1)) {
                intersecao.add(usuario1);
            }
        }

        return intersecao;
    }
}
