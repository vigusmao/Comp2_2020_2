import java.util.ArrayList;
import java.util.List;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Figurinha> figurinhasColadas;  // direct addressing

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.figurinhasColadas = new ArrayList<>();
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.figurinhasColadas.add(null);
        }
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Figurinha[] figurinhasDoPacotinho = pacotinho.getFigurinhas();
        if (figurinhasDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }


    }

    public Figurinha getItemColado(int posicao) {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public boolean possuiItemColado(int posicao) {
        return false;  // ToDo IMPLEMENT ME!!!
    }

    public boolean possuiItemRepetido(int posicao) {
        return false;  // ToDo IMPLEMENT ME!!!
    }

    public int getTamanho() {
        return this.repositorio.getTotalFigurinhas();
    }

    public int getQuantItensColados() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        // ToDo IMPLEMENT ME!!!
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> meuArrayList = new ArrayList<>(200);
//
//        // inicializa as posi'c~oes com nulls, para poder acess-las diretamente
//        for (int i = 0; i < 200; i++) {
//            meuArrayList.add(null);
//        }
//
////        System.out.println(meuArrayList.get(3));
//
//        meuArrayList.add(3, 300000);  // insert com shift right
//
//        for (int numero : meuArrayList) {
//            System.out.println(numero);
//        }
//    }
}
