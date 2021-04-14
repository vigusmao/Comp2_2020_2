import java.util.Calendar;

public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    String instituicaoDeEnsino;

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

        System.out.println("Ano corrente = " + obterPeriodoCorrente());

        Disciplina calculo1 = new Disciplina("Cálculo 1", 6, "MAA323");

        MonitorDeDisciplina fulano;
        fulano = new MonitorDeDisciplina();//1234, "Fulano de Tal", calculo1);

        System.out.println("Aluno: " + fulano);  // fulano.toString() será chamado automaticamente
    }
}
