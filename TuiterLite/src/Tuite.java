import java.util.Set;

public class Tuite {

    private final Usuario autor;
    private final String texto;
    private final Set<String> hashtags;
    private Object anexo;

    public Tuite(Usuario autor, String texto, Set<String> hashtags) {
        this.autor = autor;
        this.texto = texto;
        this.hashtags = hashtags;
    }

    public void anexarAlgo(Object anexo) {
        this.anexo = anexo;
    }

    public Object getAnexo() {
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
