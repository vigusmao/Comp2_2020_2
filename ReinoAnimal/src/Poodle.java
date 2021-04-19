public class Poodle extends Cachorro {


    @Override
    public void latir(int volumeEmDecibeis) {
        System.out.println(
                String.format("Latindo fininho (e irritantemente) a %d decibéis...",
                        volumeEmDecibeis));

    }

    @Override
    public void emitirSom() {
        latir(90);
    }
}
