import java.util.HashMap;
import java.util.Map;

public class Turma {

    public static final float MEDIA_NAO_INFORMADA = -1;

    private final Disciplina disciplina;

    private final Periodo periodo;

    private Professor professor;

    private Map<Aluno, Float> mediaByAluno;

    public Turma(Disciplina disciplina, Periodo periodo) {
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.mediaByAluno = new HashMap<>();
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
        this.mediaByAluno.put(aluno, MEDIA_NAO_INFORMADA);
    }

    public void atribuirMediaFinal(Aluno aluno, float nota) {
        if (this.mediaByAluno.get(aluno) == null) {
            // o aluno não está inscrito na turma!
            // ToDo lançaria exceção
            return;
        }
        this.mediaByAluno.put(aluno, nota);
    }

    public float obterMediaFinal(Aluno aluno) {
        Float media = this.mediaByAluno.get(aluno);
        if (media == null) {
            // ToDo lançaria exceção pois o aluno sequer pertence à turma
        }
        return media;
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
        for (Map.Entry<Aluno, Float> chaveValor : this.mediaByAluno.entrySet()) {
            Aluno aluno = chaveValor.getKey();
            float media = chaveValor.getValue();
            sb.append("\n")
                    .append(aluno.getDre())
                    .append(" - ")
                    .append(aluno.getNome())
                    .append(" - ")
                    .append(media);
        }
        return sb.toString();
    }
}
