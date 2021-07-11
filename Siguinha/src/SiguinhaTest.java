import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiguinhaTest {

    private Siguinha siguinha;

    @Before
    public void setUp() {
        this.siguinha = new Siguinha("UFRJ");
    }

    @Test
    public void testarInscricaoDeAlunoEmDisciplina() {

    }

    @Test
    public void testarInscricaoDeAlunoComDreInvalido() {
        Disciplina calculo1 = new Disciplina("Cálculo 1", 5, "blah");
        Turma turma = new Turma(calculo1, new Periodo(2020, 1));
        try {
            siguinha.inscreverAlunoEmTurma(36523564, turma);
            fail("Dres inválidos precisam ser notificados!");

        } catch (DreDesconhecidoException e) {
            assertEquals(36523564, e.getDreDesconhecido());
        }
    }

    @Test
    public void testarCategoria() {
        Aluno aluno = new Aluno(123456, "Fulano");
        if (aluno.getCategoria() == TipoAluno.GRADUACAO) {
            // faça algo com esse aluno de graduação
        } else if (aluno.getCategoria() == TipoAluno.DOUTORADO) {
            // faca algo com esse aluno de doutorado
        } // etc.

        switch (aluno.getCategoria()) {
            case GRADUACAO:
                // faça algo com esse aluno de graduação
                break;
            case DOUTORADO:
                // faça algo com esse aluno de doutorado
                break;
            default:
                // outros casos...
        }
    }
}