public class Disciplina {

    private String nome;

    private int creditos;

    private String codigo;

    public Disciplina(String nome, int creditos, String codigo) {
        this.nome = nome;
        this.creditos = creditos;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }
}
