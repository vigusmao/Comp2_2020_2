import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album<T extends Colecionavel> {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio<T> repositorio;
    private final int quantItensPorPacotinho;

    private List<T> itensColados;  // direct addressing
    private int quantItensColados;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio<T> repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.itensColados = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.itensColados.add(null);
        }
        this.quantItensColados = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho<T> pacotinho) {
        T[] itensDoPacotinho = pacotinho.getItens();
        if (itensDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (T item : itensDoPacotinho) {

            final int posicao = item.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                colarItem(posicao, item);
            }
        }
    }

    public T getItemColado(int posicao) {
        return posicao < 1 || posicao > getTamanho() ? null :
                this.itensColados.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        return getItemColado(posicao) != null;
    }

    public boolean possuiItemRepetido(int posicao) {
        int cont = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
        return cont > 0;
    }

    public int getTamanho() {
        return this.repositorio.getTotalFigurinhas();
    }

    public int getQuantItensColados() {
        // melhor jeito: atributo!
        return this.quantItensColados;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        int minimoParaAutoCompletar = (int) Math.ceil(getTamanho() * PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100.0);

        if (this.quantItensColados < minimoParaAutoCompletar) {
            return;  // não é possível ainda auto-completar
                     // ToDo: lançaria uma exceção
        }

        for (int i = 1; i <= getTamanho(); i++) {
            if (!possuiItemColado(i)) {
                T item = this.repositorio.getItem(i);
                colarItem(i, item);
            }
        }
    }

    private void colarItem(int i, T item) {
        this.itensColados.set(i, item);
        this.quantItensColados++;
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }
}
