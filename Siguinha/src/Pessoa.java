public class Pessoa {

    private String nome;

    private int anoNascimento;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getIdade() {
        if (anoNascimento == 0) {
            // ToDo lançaria uma exceção
            return -1;
        }
        return Siguinha.obterAnoCorrente() - anoNascimento;
    }
}
