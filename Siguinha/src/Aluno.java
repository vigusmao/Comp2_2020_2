import java.util.Calendar;

public class Aluno {

    // --------------------------------
    // atributos
    // --------------------------------

    private String nome;

    private final long dre;

    private float cra;
    private float numeradorCra;
    private float denominadorCra;

    private int creditosAcumulados;

    private Periodo periodoIngresso;

    private ItemHistorico[] historico;
    private int contItensHistorico;

    public final static int TAMANHO_MAXIMO_DO_NOME = 30;

    // --------------------------------
    // métodos
    // --------------------------------

    public Aluno(long dre, String nome) {

        this.dre = dre;
        this.nome = nome;

        this.historico = new ItemHistorico[4];
        this.contItensHistorico = 0;

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        this.periodoIngresso = new Periodo(
                ano, mes <= 6 ? 1 : 2);

        this.cra = 0;  // desnecessário, pois 0 é o valor default de float
        this.numeradorCra = 0;
        this.denominadorCra = 0;

        this.creditosAcumulados = 0;  // idem para int
    }

//    public Aluno() {
//        super();
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_DO_NOME) {
            // ToDo: lançar uma exceção!!!
            return;
        }

        this.nome = nome;
    }

    public float getCra() {
        return cra;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }


    // ATENÇÃO: NÃO QUEREMOS UM SETTER PÚBLICO PARA O CRA!!!!!!
//
//    public void setCra(float cra) {
//        if (cra < 0 || cra > 10) {
//            return;
//        }
//        this.cra = cra;
//    }
//

    public void inserirItemHistorico(
            Disciplina disciplina, float mediaFinal, Periodo periodo) {

        boolean disciplinaJaExistenteNoPeriodo = false;

        // verifica se já existe no histórico essa disciplina nesse período
        for (ItemHistorico itemHistorico : this.historico) {

            if (itemHistorico == null) {
                break;
            }

            if (itemHistorico.disciplinaCursada == disciplina &&
                    itemHistorico.periodo == periodo) {

                disciplinaJaExistenteNoPeriodo = true;

                int creditosDaDisciplina = itemHistorico.disciplinaCursada.getCreditos();
                this.numeradorCra -= itemHistorico.mediaFinal * creditosDaDisciplina;
                if (itemHistorico.mediaFinal >= Siguinha.MEDIA_MINIMA_PARA_APROVACAO) {
                    this.creditosAcumulados -= creditosDaDisciplina;
                }

                itemHistorico.mediaFinal = mediaFinal;
            }
        }

        if (!disciplinaJaExistenteNoPeriodo) {
            // inserir item no histórico

            ItemHistorico novoItem = new ItemHistorico(
                    disciplina, mediaFinal, periodo);
            if (this.contItensHistorico == this.historico.length) {
                // TRATAR OVERFLOW: copiar tudo para um array maior

            }
            this.historico[this.contItensHistorico++] = novoItem;
        }

        // atualizar creditos
        if (mediaFinal >= Siguinha.MEDIA_MINIMA_PARA_APROVACAO) {
            this.creditosAcumulados += disciplina.getCreditos();
        }

        // atualizar CRA:

//        float numerador = 0;
//        float denominador = 0;
//        for (ItemHistorico itemHistorico : this.historico) {
//            int creditos = itemHistorico.disciplinaCursada.getCreditos();
//            numerador += itemHistorico.mediaFinal * creditos;
//            denominador += creditos;
//        }
//        this.cra = numerador / denominador;

        // outro jeito de atualizar o CRA (melhor performance)
        this.numeradorCra += mediaFinal * disciplina.getCreditos();
        this.denominadorCra += disciplina.getCreditos();
        this.cra = this.numeradorCra / this.denominadorCra;
    }

    public String getHistoricoParaImpressao() {
        String resultado = "Aluno(a): " + this.nome +
                " (DRE: " + this.dre + ")\n";
        for (int i = 0; i < this.contItensHistorico; i++) {
            ItemHistorico itemHistorico = this.historico[i];
            resultado += itemHistorico.periodo.getAno();
            resultado += ".";
            resultado += itemHistorico.periodo.getSemestre();
            resultado += " - ";
            resultado += itemHistorico.disciplinaCursada.getNome();
            resultado += " - ";
            resultado += String.format("%.1f", itemHistorico.mediaFinal);
            if (i != this.contItensHistorico - 1) {  // se não é o último item...
                resultado += "\n";
            }
        }
        return resultado;
    }

    // inner class (classe auxiliar, visível apenas de dentro da classe Aluno)
    private class ItemHistorico {

        public Disciplina disciplinaCursada;

        float mediaFinal;

        Periodo periodo;

        ItemHistorico(Disciplina disciplinaCursada, float mediaFinal, Periodo periodo) {
            this.disciplinaCursada = disciplinaCursada;
            this.mediaFinal = mediaFinal;
            this.periodo = periodo;
        }
    }

}
