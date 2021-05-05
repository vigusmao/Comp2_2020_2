import java.util.HashMap;
import java.util.Map;

public class Turma {

    private final Disciplina disciplina;

    private final Periodo periodo;

    private Professor professor;

    private Map<Long, Aluno> alunoByDre;

    private Map<Long, Float> mediaByDre;

    public Turma(Disciplina disciplina, Periodo periodo) {
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.alunoByDre = new HashMap<>();
        this.mediaByDre = new HashMap<>();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    void inscreverAluno(Aluno aluno) {
        this.alunoByDre.put(aluno.getDre(), aluno);
    }

    public void atribuirMediaFinal(long dre, float nota) {
        if (this.alunoByDre.get(dre) == null) {
            // o aluno não está inscrito na turma!
            // ToDo lançaria exceção
            return;
        }
        this.mediaByDre.put(dre, nota);
    }

    public float obterMediaFinal(long dre) {
        return this.mediaByDre.get(dre);
    }

    public String listarAlunos() {
//        String resultado = "";
//
//        for (Aluno aluno : this.alunoByDre.values()) {
//            long dre = aluno.getDre();
//            resultado += "\n" + dre + " - " + aluno.getNome() +
//                    " - " + this.mediaByDre.get(dre);
//        }
//        return resultado;

        StringBuilder sb = new StringBuilder();
        for (Aluno aluno : this.alunoByDre.values()) {
            long dre = aluno.getDre();
            sb.append("\n")
                    .append(dre)
                    .append(" - ")
                    .append(aluno.getNome())
                    .append(" - ")
                    .append(this.mediaByDre.get(dre));
        }
        return sb.toString();
    }
}
