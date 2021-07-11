package controle;

public class Config {

    // Pontuação inicial de um usuário ao se cadastrar no sistema
    public static final int PONTUACAO_INICIAL = 1000;

    // Percentual mínimo de diferença para que as pontuações de dois jogadores NÃO
    // sejam consideradas semelhantes
    public static final int PERCENTUAL_PARA_SIMILARIDADE = 1;  // 1%

    // Variação absoluta da pontuação do jogador mais forte quando perde do jogador mais fraco,
    // e também do jogador mais fraco quando ganha do mais forte
    public static final int DELTA_GRANDE = 8;

    // Variação absoluta da pontuação do vencedor e do perdedor de uma partida (para cima e
    // para baixo, respectivemente), quando ambos tem pontuação semelhante (isto é, são considerados
    // jogadores de mesma força relativa)
    public static final int DELTA_MEDIO = 5;

    // Variação absoluta da pontuação do jogador mais forte quando ganha do jogador mais fraco,
    // e também do jogador mais fraco quando perde do mais forte
    public static final int DELTA_PEQUENO = 2;

    // Variação absoluta da pontuação do jogador mais forte e do jogador mais fraco (para baixo e
    // para cima, respectivemente), em caso de empate
    public static final int DELTA_MINIMO = 1;
}
