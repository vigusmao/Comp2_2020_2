import java.util.Set;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private final Set<String> hashtags;
    private T anexo;

    public Tuite(Usuario autor, String texto, Set<String> hashtags) {
        this.autor = autor;
        this.texto = texto;
        this.hashtags = hashtags;
    }

    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    public T getAnexo() {
        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public Set<String> getHashtags() {
        return this.hashtags;
    }
}
