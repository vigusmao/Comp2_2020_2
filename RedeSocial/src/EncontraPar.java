import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EncontraPar {

    public void encontrarParIngenuo(List<Integer> lista, int somaDesejada) {
        for (int i = 0; i < lista.size(); i++) {
            int elemento = lista.get(i);
            for (int j = i+1; j < lista.size(); j++) {
                int outroElemento = lista.get(j);
                if (elemento + outroElemento == somaDesejada) {
                    System.out.println(
                            String.format("%d + %d = %d",
                                    elemento, outroElemento, somaDesejada));
                    return;
                }
            }
        }
    }

    public void encontrarParEsperto(List<Integer> lista, int somaDesejada) {
        Set<Integer> conjunto = new HashSet<>();

        for (int i = 0; i < lista.size(); i++) {
            int elemento = lista.get(i);
            conjunto.add(elemento);
        }

        for (int i = 0; i < lista.size(); i++) {
            int elemento = lista.get(i);
            int complementar = somaDesejada - elemento;
            if (conjunto.contains(complementar)) {
                System.out.println(
                        String.format("%d + %d = %d",
                                elemento, complementar, somaDesejada));
                return;
            }
        }
    }
}

