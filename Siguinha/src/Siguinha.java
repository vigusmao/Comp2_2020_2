import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    String instituicaoDeEnsino;

    Map<Long, Aluno> alunoByDre;



    public Siguinha(String instituicaoDeEnsino) {
        this.instituicaoDeEnsino = instituicaoDeEnsino;
        this.alunoByDre = new HashMap<>();
    }

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

    public String getInstituicaoDeEnsino() {
        return instituicaoDeEnsino;
    }

    public void setInstituicaoDeEnsino(String instituicaoDeEnsino) {
        this.instituicaoDeEnsino = instituicaoDeEnsino;
    }

    public void cadastrarAluno(long dre, String nome) {
        Aluno aluno = this.alunoByDre.get(dre);
        if (aluno == null) {
            aluno = new Aluno(dre, nome);
            this.alunoByDre.put(dre, aluno);
        } else {
            // já existia no cadastro, então apenas atualiza o nome
            aluno.setNome(nome);
        }
    }

    public Aluno obterAluno(long dre) {
        return this.alunoByDre.get(dre);
    }

    public void abrirTurma(Disciplina disciplina, Professor professor) {
        Turma turma = new Turma(disciplina, obterPeriodoCorrente());
        // ToDo adicionar a nova turma numa coleção de turmas
    }

    public void inscreverAlunoEmTurma(long dre, Turma turma)
            throws DreDesconhecidoException {

        Aluno aluno = this.alunoByDre.get(dre);
        if (aluno == null) {
            throw new DreDesconhecidoException(dre);
        }
        turma.inscreverAluno(aluno);
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

    }
}
