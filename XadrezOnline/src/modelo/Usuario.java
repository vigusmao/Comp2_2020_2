package modelo;

import java.util.List;
import java.util.Objects;

public class Usuario {

    private String username;
    private int hashSenha;
    private boolean logado;

    public Usuario(String username, String senha) {
        this.username = username;
        this.hashSenha = obterHash(senha);
        this.logado = false;
    }

    private static int obterHash(String senha) {
        // não se deve gravar diretamente uma senha pessoal, mas sim um "hash" da senha
        return Objects.hash(senha);
    }

    public int getPontuacao(Modalidade modalidade) {
        return 0;  // ToDo IMPLEMENT ME!
    }

    public int getContVitorias(Modalidade modalidade) {
        return 0;  // ToDo IMPLEMENT ME!
    }

    public int getContDerrotas(Modalidade modalidade) {
        return 0;  // ToDo IMPLEMENT ME!
    }

    public int getContEmpates(Modalidade modalidade) {
        return 0;  // ToDo IMPLEMENT ME!
    }

    public String getUsername() {
        return null;  // ToDo IMPLEMENT ME!
    }

    public boolean validarSenha(String senhaASerValidada) {
        return this.hashSenha == obterHash(senhaASerValidada);
    }

    public List<Partida> getHistoricoPartidas() {
        return null;  // ToDo IMPLEMENT ME!
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public void aceitarConvites(Modalidade modalidade) {

    }

    public void naoAceitarConvites(Modalidade modalidade) {

    }

    /**
     * Inclui nova partida no histórico deste usuário, atualizando seus contadores
     * para a modalidade de jogo da partida informada.
     *
     * @param partida A partida concluída onde um dos jogadores é necessariamente este usuário.
     */
    public void adicionarPartidaConcluidaAoHistorico(Partida partida) {
        // ToDo IMPLEMENT ME!
    }
}
