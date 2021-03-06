package controle;

import controle.excecoes.*;
import modelo.Modalidade;
import modelo.Partida;
import modelo.ResultadoPartida;
import modelo.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Esboço de plataforma para jogo de xadrez online. Cada partida é disputada por dois jogadores,
 * um jogando com as peças brancas, outro jogando com as peças negras.
 *
 * Há quatro modalidades (controles de tempo) diferentes, que estão descritos no enum Modalidade.
 *
 * Cada usuário tem uma pontuação para cada modalidade de jogo. Por exemplo, certo usuário pode
 * ter 1500 pontos na modalidade CLASSIC, mas apenas 1280 pontos na modalidade BULLET, etc.
 */
public class XadrezOnline {

    private Map<String, Usuario> usuarioByNome;

    private Map<Modalidade, Set<Usuario>> usuarioAceitandoConviteByModalidade;

    public XadrezOnline() {
        this.usuarioByNome = new HashMap<>();
    }

    /**
     * Cadastra um usuário. O username precisa ser único.
     *
     * @param username o nome do usuário (único) no sistema
     * @param senha a senha a ser cadastrada para aquele usuário
     *
     * @return o novo Usuario cadastrado
     * @throws UsuarioJaExisteException se o usuário já estiver cadastrado no sistema
     */
    public Usuario cadastrarUsuario(String username, String senha)
            throws UsuarioJaExisteException {

        if (this.usuarioByNome.containsKey(username)) {
            throw new UsuarioJaExisteException();
        }

        this.usuarioByNome.put(username, new Usuario(username, senha));
    }

    /**
     * Loga um usuário no sistema. Quando o usuário se loga, ele passa a poder:
     * - escolher um adversário para jogar uma partida, ou
     * - se fazer disponível para ser escolhido pelo sistema como adversário
     *   de outro usuário que deseja jogar.
     *
     * @param username o nome do usuário
     * @param senha a senha do usuário para validação
     *
     * @throws UsuarioDesconhecidoException se o usuário não existir no sistema
     * @throws SenhaIncorretaException se a senha informada foi incorreta
     */
    public void logIn(String username, String senha)
            throws UsuarioDesconhecidoException, SenhaIncorretaException {

        Usuario usuario = this.usuarioByNome.get(username);

        if (usuario == null) {
            throw new UsuarioDesconhecidoException();
        }

        if (!usuario.validarSenha(senha)) {
            throw new SenhaIncorretaException();
        }

        usuario.setLogado(true);
    }

    /**
     * Termina a sessão de um usuário no sistema.
     *
     * @param username o identificador (nome) do usuário
     *
     * @throws UsuarioDesconhecidoException se o usuário não existir no sistema
     * @throws UsuarioNaoLogadoException se o usuário não está logado (online no sistema)
     */
    public void logOut(String username)
            throws UsuarioDesconhecidoException, UsuarioNaoLogadoException {

        Usuario usuario = this.usuarioByNome.get(username);

        if (usuario == null) {
            throw new UsuarioDesconhecidoException();
        }

        if (!usuario.isLogado()) {
            throw new UsuarioNaoLogadoException();
        }

        usuario.setLogado(false);
    }

    /**
     * Sugere um adversário para o jogador informado, na modalidade informada.
     *
     * O adversário deverá ser escolhido segundo os critérios/condições abaixo:
     * - deverá estar online (isto é, deve estar logado no sistema);
     * - deverá estar em modo "aceitando convites" para a modalidade desejada;
     * - deve ser aquele, dentre os que atendam os critérios acima, que possua a pontuação
     *   mais próxima possível (acima ou abaixo ou igual) à do jogador informado.
     *
     * @param modalidade A modalidade de jogo desejada
     * @param usuario O usuário para o qual desejamos escolher um adversário
     *
     * @return o adversário sugerido, ou null, se não houver qualquer adversário disponível
     */
    public Usuario sugerirAdversario(Enum modalidade, Usuario usuario) {
        return null;  // ToDo IMPLEMENT ME!
    }

    /**
     * Inicia uma partida entre os usuários informados.
     *
     * @param modalidade O controle de tempo desejado
     * @param jogador1 O usuário que jogará com as peças brancas
     * @param jogador2 O usuário que jogará com as peças negras
     *
     * @return A nova Partida a ser iniciada
     * @throws UsuarioOcupadoException se um dos usuários já estiver envolvido em alguma partida em andamento
     */
    public Partida iniciarPartida(Usuario jogador1, Usuario jogador2, Modalidade modalidade)
            throws UsuarioOcupadoException {
        return new Partida(jogador1, jogador2, modalidade);
    }

    public void concluirPartida(Partida partida, ResultadoPartida resultado) throws PartidaJaConcluidaException {
        if (partida.getResultado() != null) {
            throw new PartidaJaConcluidaException();
        }
        partida.setResultado(resultado);
        if (resultado != ResultadoPartida.PARTIDA_CANCELADA) {
            atualizarPontuacoes(partida);
        }
    }

    /**
     * A pontuação deve ser atualizada da seguinte maneira:
     *
     * - caso os dois jogadores tenham pontuação semelhante (isto é, caso a DIFERENÇA ABSOLUTA entre suas
     *   pontuações na modalidade da partida informada seja INFERIOR A {@link Config#PERCENTUAL_PARA_SIMILARIDADE}%
     *   da pontuação do jogador de maior pontuação dentre aqueles dois jogadores, naquela modalidade), então:
     *   --> o vencedor deve GANHAR {@link Config#DELTA_MEDIO} PONTOS naquela modalidade, e
     *   --> o perdedor deve PERDER {@link Config#DELTA_MEDIO} PONTOS naquela modalidade;
     *   --> em caso de empate as pontuações permanecem inalteradas.
     *
     * - caso contrário,
     *   --> o jogador de MAIOR pontuação na modalidade da partida informada (dentre os dois jogadores da partida)
     *       deve GANHAR {@link Config#DELTA_PEQUENO} PONTOS naquela modalidade em caso de vitória sua na partida, e
     *       deve PERDER {@link Config#DELTA_GRANDE} PONTOS naquela modalidade em caso de derrota sua na partida;
     *   --> o jogador de MENOR pontuação na modalidade da partida informada (dentre os dois jogadores da partida)
     *       deve GANHAR {@link Config#DELTA_GRANDE} PONTOS naquela modalidade em caso de vitória sua na partida, e
     *       deve PERDER {@link Config#DELTA_PEQUENO} PONTOS naquela modalidade em caso de derrota sua na partida;
     *   --> em caso de empate o jogador de MAIOR pontuação PERDE {@link Config#DELTA_MINIMO} pontos,
     *       e o jogador de MENOR pontuação GANHA {@link Config#DELTA_MINIMO} pontos.
     *
     */
    private void atualizarPontuacoes(Partida partidaConcluida) {

    }
}
