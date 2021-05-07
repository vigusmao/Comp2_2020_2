import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalculadorIntersecaoViaBuscaBinaria implements CalculadorIntersecao {


    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        List<Usuario> intersecao = new ArrayList<>();

//        // ordena a primeira lista
//        Comparator<Usuario> c = new ComparadorUsuarios();
//        lista1.sort(c);

        // outra maneira (prefiro, não precisa criar um Comparator)
        Collections.sort(lista1);

        // para cada elemento da segunda lista, busca na primeira via Busca Binária
        for (Usuario usuarioDaSegundaLista : lista2) {
            if (Collections.binarySearch(lista1, usuarioDaSegundaLista) >= 0) {
                // a busca binária encontrou o elemento
                intersecao.add(usuarioDaSegundaLista);
            }
        }

        return intersecao;
    }
}
