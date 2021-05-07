import java.util.Comparator;


public class ComparadorUsuarios implements Comparator<Usuario> {

    @Override
    public int compare(Usuario o1, Usuario o2) {
        return o1.getId() - o2.getId();
        // será positivo, se o primeiro for maior;
        // será zero, se o primeiro for igual ao segundo;
        // será negativo, se for menor.
    }
}
