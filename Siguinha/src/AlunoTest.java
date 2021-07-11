import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class AlunoTest {

    private static final float MAX_FLOAT_DIFF = 0.000001f;

    private Aluno fulana;
    private Disciplina algGraf;
    private Disciplina calculo1;
    private Periodo periodo20201;
    private Periodo periodo20202;

    private DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
    private char decimalSeparator = decimalFormat.getDecimalFormatSymbols().getDecimalSeparator();

    @Before
    public void setUp() {
        fulana = new Aluno(1234, "Fulana de Tal");
        algGraf = new Disciplina("Algoritmos e Grafos", 4, "MAB704");
        calculo1 = new Disciplina("Calculo 1", 4, "MAB704");
        periodo20201 = new Periodo(2020, 1);
        periodo20202 = new Periodo(2020, 2);
    }

    @Test
    public void testarAtualizacaoCraComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                10, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos das disciplinas em que houve aprovação",
                4, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Algoritmos e Grafos - 10" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarAtualizacaoCraComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                3, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos APENAS das disciplinas em que houve aprovação",
                0, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasNoMesmoPeriodo() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 4, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20201);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasEmPeriodosDistintos() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20202);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0\n" +
                "2020.2 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());

    }

    @Test
    public void testarInsersacoDeUmNumeroMuitoGrandeDeDisciplinas() {
        for (int i = 0; i < 10_000; i++) {
            Disciplina disciplina = new Disciplina("blah" + i, 4, "MAB" + i);
            fulana.inserirItemHistorico(disciplina, 6, periodo20201);
        }
    }
}