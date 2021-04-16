public class Cachorro extends Mamifero {

    public Cachorro() {
        super("Canídeo");
    }

    private String raca;

    public void roerOsso() {
        System.out.println("Roendo osso...");
    }

    public void latir(int volumeEmDecibeis) {
        System.out.println("Latindo a " + volumeEmDecibeis + "decibéis...");
    }

    @Override
    public void emitirSom() {
        latir(20);
    }

    public void pular() {
        System.out.println("Pulando...");
    }
}
