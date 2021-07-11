public class Morcego extends Mamifero {

    public Morcego() {
        super("Morceguídeo");
        // ... aqui entraria qualquer código de verdade
        System.out.println("Morcego criado! Vou ficar de cabeça para baixo pela primeira vez...");
    }

    public void voar() {
        System.out.println("Voando...");
    }

//    @Override
    public void emitirSom() {
        System.out.println("Emitindo ultrassom de morcego...");
    }
}
