package test;

import controle.XadrezOnline;
import controle.excecoes.*;
import modelo.Modalidade;
import modelo.Partida;
import modelo.ResultadoPartida;
import modelo.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XadrezOnlineTest {

    private XadrezOnline xadrezOnline;
    private Usuario usuario1, usuario2, usuario3, usuario4;

    private String senhaUsuario1 = "e4e5Nf3Nc6Bb5a6Ba4Nf6O-O";
    private String senhaUsuario2 = "e4e5Nf3Nc6Bc4Nf6Ng5d5exd5Na5";
    private String senhaUsuario3 = "1/2-1/2";
    private String senhaUsuario4 = "d4d5c4dxc4e5";

    @Before
    public void setUp() throws Exception {
        xadrezOnline = new XadrezOnline();

        usuario1 = xadrezOnline.cadastrarUsuario("bobby_carlsen", senhaUsuario1);
        usuario2 = xadrezOnline.cadastrarUsuario("fabiano_nepomniachtchi", senhaUsuario2);
        usuario3 = xadrezOnline.cadastrarUsuario("paul_giri", senhaUsuario3);
        usuario4 = xadrezOnline.cadastrarUsuario("vishwanatan_capablanca", senhaUsuario4);
    }

    @Test
    public void testarCadastrarUsuario() {
        try {
            xadrezOnline.cadastrarUsuario(usuario1.getUsername(), "xxxxxxxxx");
            fail("Ao se cadastrar um usuário já existente, uma UsuarioJaExisteException deve ser lançada");
        } catch (UsuarioJaExisteException e) {
            // pass
        }
    }

    @Test
    public void testarLogInELogOut() throws UsuarioDesconhecidoException, SenhaIncorretaException {
        xadrezOnline.logIn(usuario1.getUsername(), senhaUsuario1);

        try {
            xadrezOnline.logIn(usuario2.getUsername(), "blablablablabla");
            fail("Ao se informar uma senha incorreta, uma SenhaIncorretaException deve ser lançada");
        } catch (SenhaIncorretaException e) {
            // pass
        }

        try {
            xadrezOnline.logIn("U.N.Owen", "10n...0n");
            fail("Ao se tentar o log in com usuário desconhecido, uma UsuarioDesconhecidoException deve ser lançada");
        } catch (UsuarioDesconhecidoException e) {
            // pass
        }

        try {
            xadrezOnline.logOut(usuario3.getUsername());
            fail("Ao se tentar o log out com usuário não logado, uma UsuarioNaoLogadoException deve ser lançada");
        } catch (UsuarioNaoLogadoException e) {
            // pass
        }
    }

    @Test
    public void sugerirAdversario()
            throws UsuarioDesconhecidoException, SenhaIncorretaException, PartidaJaConcluidaException,
            UsuarioNaoLogadoException, UsuarioOcupadoException {

        fazerLoginDeTodosOsUsuarios();

        Partida partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.CLASSICAL);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);
        partida = xadrezOnline.iniciarPartida(usuario3, usuario4, Modalidade.CLASSICAL);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_NEGRAS);

        assertNull("Quando não há jogadores logados o adversário sugerido deve ser null",
                xadrezOnline.sugerirAdversario(Modalidade.CLASSICAL, usuario3));

        usuario1.aceitarConvites(Modalidade.CLASSICAL);
        usuario2.aceitarConvites(Modalidade.CLASSICAL);
        usuario3.aceitarConvites(Modalidade.CLASSICAL);
        usuario4.aceitarConvites(Modalidade.CLASSICAL);

        final String mensagemCriterioAdversario = "Dentre os jogadores que podem ser escolhidos como adversário, " +
                "deve-se selecionar aquele com pontuação mais próxima da pontuação do jogador-alvo";
        assertEquals(mensagemCriterioAdversario,
                usuario2, xadrezOnline.sugerirAdversario(Modalidade.CLASSICAL, usuario3));
        assertEquals(mensagemCriterioAdversario,
                usuario4, xadrezOnline.sugerirAdversario(Modalidade.CLASSICAL, usuario1));

        usuario1.naoAceitarConvites(Modalidade.CLASSICAL);
        xadrezOnline.logOut(usuario2.getUsername());
        assertEquals("Apenas usuários online e que estejam aceitando convites para a modalidade desejada " +
                "podem ser sugeridos", usuario4, xadrezOnline.sugerirAdversario(Modalidade.CLASSICAL, usuario3));

        xadrezOnline.iniciarPartida(usuario1, usuario4, Modalidade.CLASSICAL);
        assertNull("Quando não há nenhum outro usuário online aceitando convites para a modalidade desejada, " +
                "o adversário sugerido deve ser null", xadrezOnline.sugerirAdversario(Modalidade.CLASSICAL, usuario3));

    }

    @Test
    public void testarIniciarPartida()
            throws UsuarioDesconhecidoException, SenhaIncorretaException, UsuarioOcupadoException {

        fazerLoginDeTodosOsUsuarios();

        xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);

        try {
            xadrezOnline.iniciarPartida(usuario3, usuario1, Modalidade.BLITZ);
            fail("Um usuário não pode jogar mais de uma partida ao mesmo tempo, mesmo que de diferentes " +
                    "modalidades -- nesse caso uma UsuarioOcupadoException deve ser lançada");
        } catch (UsuarioOcupadoException e) {
            // pass
        }
    }

    @Test
    public void testarConcluirPartida()
            throws UsuarioDesconhecidoException, SenhaIncorretaException, UsuarioOcupadoException,
            PartidaJaConcluidaException {

        fazerLoginDeTodosOsUsuarios();

        Partida partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);

        try {
            xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);
            fail("Não deve ser possível concluir novamente uma partida já concluída");
        } catch (PartidaJaConcluidaException e) {
            // pass
        }
    }

    @Test
    public void testarAtualizacaoContadores()
            throws UsuarioDesconhecidoException, SenhaIncorretaException, UsuarioOcupadoException,
            PartidaJaConcluidaException {

        fazerLoginDeTodosOsUsuarios();

        Partida partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);

        assertEquals(1, usuario1.getContVitorias(Modalidade.RAPID));
        assertEquals(0, usuario1.getContDerrotas(Modalidade.RAPID));
        assertEquals(0, usuario1.getContEmpates(Modalidade.RAPID));
        assertEquals(0, usuario1.getContVitorias(Modalidade.BLITZ));

        assertEquals(0, usuario2.getContVitorias(Modalidade.RAPID));
        assertEquals(1, usuario2.getContDerrotas(Modalidade.RAPID));
        assertEquals(0, usuario2.getContEmpates(Modalidade.RAPID));
        assertEquals(0, usuario2.getContDerrotas(Modalidade.BLITZ));

        partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.BULLET);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.EMPATE);

        assertEquals(1, usuario1.getContEmpates(Modalidade.BULLET));
        assertEquals(1, usuario2.getContEmpates(Modalidade.BULLET));
        assertEquals(0, usuario3.getContEmpates(Modalidade.BULLET));
        assertEquals(0, usuario1.getContEmpates(Modalidade.RAPID));
    }

    @Test
    public void testarPontuacaoInicial() {
        for (Modalidade modalidade : Modalidade.values()) {
            assertEquals("A pontuação inicial de cada jogador ao entrar no sistema deve ser de " +
                            XadrezOnline.PONTUACAO_INICIAL + " pontos",
                    XadrezOnline.PONTUACAO_INICIAL, usuario1.getPontuacao(modalidade));
        }
    }

    @Test
    public void testarAtualizacaoPontuacaoJogadoresSimilares()
            throws UsuarioDesconhecidoException, SenhaIncorretaException, UsuarioOcupadoException,
            PartidaJaConcluidaException {

        fazerLoginDeTodosOsUsuarios();

        Partida partida;

        final double fator = XadrezOnline.PERCENTUAL_PARA_SIMILARIDADE / 100.0;

        int pontuacaoCorrenteUsuario1 = XadrezOnline.PONTUACAO_INICIAL;
        int pontuacaoCorrenteUsuario2 = XadrezOnline.PONTUACAO_INICIAL;

        // enquanto ambos os jogadores possuem pontuações próximas...
        while (Math.abs(pontuacaoCorrenteUsuario1 - pontuacaoCorrenteUsuario2) <
            Math.max(pontuacaoCorrenteUsuario1, pontuacaoCorrenteUsuario2) * fator) {

            // vitória do jogador 1
            partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
            xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);

            pontuacaoCorrenteUsuario1 += XadrezOnline.DELTA_MEDIO;
            pontuacaoCorrenteUsuario2 -= XadrezOnline.DELTA_MEDIO;

            assertEquals(pontuacaoCorrenteUsuario1, usuario1.getPontuacao(Modalidade.RAPID));
            assertEquals(pontuacaoCorrenteUsuario2, usuario2.getPontuacao(Modalidade.RAPID));

            // empate
            partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
            xadrezOnline.concluirPartida(partida, ResultadoPartida.EMPATE);

            assertEquals(pontuacaoCorrenteUsuario1, usuario1.getPontuacao(Modalidade.RAPID));
            assertEquals(pontuacaoCorrenteUsuario2, usuario2.getPontuacao(Modalidade.RAPID));
        }

        // daqui pra frente as pontuações são suficientemente distantes...

        // vitória do jogador com mais pontos
        partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_BRANCAS);

        pontuacaoCorrenteUsuario1 += XadrezOnline.DELTA_PEQUENO;
        pontuacaoCorrenteUsuario2 -= XadrezOnline.DELTA_PEQUENO;

        assertEquals(pontuacaoCorrenteUsuario1, usuario1.getPontuacao(Modalidade.RAPID));
        assertEquals(pontuacaoCorrenteUsuario2, usuario2.getPontuacao(Modalidade.RAPID));

        // empate
        partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.EMPATE);

        pontuacaoCorrenteUsuario1 -= XadrezOnline.DELTA_MINIMO;
        pontuacaoCorrenteUsuario2 += XadrezOnline.DELTA_MINIMO;

        assertEquals(pontuacaoCorrenteUsuario1, usuario1.getPontuacao(Modalidade.RAPID));
        assertEquals(pontuacaoCorrenteUsuario2, usuario2.getPontuacao(Modalidade.RAPID));

        // vitória do jogador com menos pontos
        partida = xadrezOnline.iniciarPartida(usuario1, usuario2, Modalidade.RAPID);
        xadrezOnline.concluirPartida(partida, ResultadoPartida.VITORIA_JOGADOR_NEGRAS);

        pontuacaoCorrenteUsuario1 -= XadrezOnline.DELTA_GRANDE;
        pontuacaoCorrenteUsuario2 += XadrezOnline.DELTA_GRANDE;

        assertEquals(pontuacaoCorrenteUsuario1, usuario1.getPontuacao(Modalidade.RAPID));
        assertEquals(pontuacaoCorrenteUsuario2, usuario2.getPontuacao(Modalidade.RAPID));
    }

    private void fazerLoginDeTodosOsUsuarios()
            throws UsuarioDesconhecidoException, SenhaIncorretaException {

        xadrezOnline.logIn(usuario1.getUsername(), senhaUsuario1);
        xadrezOnline.logIn(usuario2.getUsername(), senhaUsuario2);
        xadrezOnline.logIn(usuario3.getUsername(), senhaUsuario3);
        xadrezOnline.logIn(usuario4.getUsername(), senhaUsuario4);
    }
}