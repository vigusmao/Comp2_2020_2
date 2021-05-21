import java.util.Random;

public class Pacotinho {

    private Random random;
    private Colecionavel[] itens;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {

        this.itens = new Figurinha[posicoesDesejadas == null ? 0 :
                posicoesDesejadas.length];

        if (posicoesDesejadas != null) {
            preencherPacotinho(repo, posicoesDesejadas);
        }
    }

    /**
     * Produz um pacotinho com quantItens itens sorteados aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantItens a quantidade de itens a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantItens) {
        this.itens = new Colecionavel[quantItens];

        this.random = new Random();

        int[] posicoesAleatorias = new int[quantItens];
        for (int i = 0; i < quantItens; i++) {
            int posicaoSorteada = random.nextInt(repo.getTotalFigurinhas()) + 1;
            posicoesAleatorias[i] = posicaoSorteada;
        }

        preencherPacotinho(repo, posicoesAleatorias);
    }

    private void preencherPacotinho(Repositorio repo, int[] posicoesDesejadas) {
        for (int i = 0; i < posicoesDesejadas.length; i++) {
            int posicao = posicoesDesejadas[i];
            Colecionavel item = repo.getItem(posicao);
            this.itens[i] = item;
        }
    }

    public Colecionavel[] getItens() {
        return this.itens;
    }
}
