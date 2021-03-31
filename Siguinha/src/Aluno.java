import java.util.Calendar;

public class Aluno {

    // --------------------------------
    // atributos
    // --------------------------------

    private String nome;

    private long dre;

    private float cra;

    private int creditosAcumulados;

    private Periodo periodoIngresso;

    private ItemHistorico[] historico;

    // --------------------------------
    // métodos
    // --------------------------------

    public Aluno(long dre, String nome) {

        this.dre = dre;
        this.nome = nome;

        this.historico = new ItemHistorico[4];

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        this.periodoIngresso = new Periodo(
                ano, mes <= 6 ? 1 : 2);

        this.cra = 0;  // desnecessário, pois 0 é o valor default de float
        this.creditosAcumulados = 0;  // idem para int

    }

//    public Aluno() {
//        super();
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 30) {
            // ToDo: lançar uma exceção!!!
            return;
        }

        this.nome = nome;
    }

    public float getCra() {
        return cra;
    }

    public void inserirItemHistorico(
            Disciplina disciplina, float mediaFinal, Periodo periodo) {

        // ToDo IMPLEMENT ME!!!


    }



    // inner class (classe auxiliar, visível apenas de dentro da classe Aluno)
    private class ItemHistorico {

        private Aluno aluno;

        private Disciplina disciplinaCursada;

        private float mediaFinal;

        private Periodo periodo;
    }

}
