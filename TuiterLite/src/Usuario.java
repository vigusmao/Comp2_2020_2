import java.awt.*;
import java.util.Objects;

public class Usuario {

    private final String email;
    private String nome;
    private Image foto;
    private NivelUsuario nivel;
    private int contTuites;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
        this.contTuites = 0;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Image getFoto() {
        return this.foto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public NivelUsuario getNivel() {
        return this.nivel;
    }

    void incrementarContTuites() {
        this.contTuites++;
        if (contTuites >= NivelUsuario.NINJA.getMinTuites()) {
            setNivel(NivelUsuario.NINJA);
        } else if (contTuites >= NivelUsuario.SENIOR.getMinTuites()) {
            setNivel(NivelUsuario.SENIOR);
        }
    }

    private void setNivel(NivelUsuario novoNivel) {
        if (novoNivel != this.nivel) {
            this.nivel = novoNivel;
            // logar num arquivo dizendo que o nível mudou
            // mandar um e-mail para o usuário dando os parabéns pela promoção!
            // etc.
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
