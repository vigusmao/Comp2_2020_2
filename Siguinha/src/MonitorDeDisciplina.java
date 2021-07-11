public class MonitorDeDisciplina extends Aluno {

    public static final float CRA_MINIMO_PARA_RENOVACAO_MONITORIA = 6.0f;

    private Disciplina disciplina;

    public MonitorDeDisciplina() {
        super();
    }

    public MonitorDeDisciplina(long dre, String nome, Disciplina disciplina) {
        super(dre, nome);
        this.disciplina = disciplina;
    }

    public void verificarNotas() {
        // ToDo ...
    }

    public void renovarMonitoria() {
        if (this.getCra() >= CRA_MINIMO_PARA_RENOVACAO_MONITORIA) {
            // ToDo ...
        }
    }

    @Override
    public String toString() {
        return "MonitorDeDisciplina{" +
                "disciplina=" + disciplina +
                '}';
    }

    @Override
    public String getHistoricoParaImpressao() {
        String resultado = "HISTÓRICO DE MONITOR!!!!!!!!\n" +
                super.getHistoricoParaImpressao();
        return resultado;
    }
}
