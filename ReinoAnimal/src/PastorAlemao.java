public class PastorAlemao extends Cachorro {

    public void morderOInvasor() {
        // ...
    }

    @Override
    public void latir(int volumeEmDecibeis) {
        System.out.println(
                String.format("Latindo grosso para espantar o bandido a %d decibéis...",
                        volumeEmDecibeis));
    }

    @Override
    public void emitirSom() {
        latir(50);
    }
}
