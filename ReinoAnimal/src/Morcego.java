public class Morcego extends Mamifero {

    public Morcego() {
        super("Morceguídeo");
    }

    public void voar() {
        System.out.println("Voando...");
    }

    @Override
    public void emitirSom() {
        System.out.println("Emitindo ultrassom de morcego...");
    }
}
