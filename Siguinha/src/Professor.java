public class Professor extends Pessoa {

    private int anoContratacao;

    public Professor(String nome) {
        this(nome, Siguinha.obterAnoCorrente());
    }

    public Professor(String nome, int anoContratacao) {
        super(nome);
        this.anoContratacao = anoContratacao;
    }
}
