import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Comparable<Usuario> {

    private int id;

    private List<Usuario> amigos;

    private CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id) {
        this.id = id;
        this.calculadorIntersecao = new CalculadorIntersecaoEsperto();  // composição
        this.amigos = new ArrayList<>();
    }

    public void setCalculadorIntersecao(CalculadorIntersecao calculador) {
        this.calculadorIntersecao = calculador;
    }

    public int getId() {
        return id;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    public void adicionarAmigo(Usuario usuario2) {
        amigos.add(usuario2);
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
