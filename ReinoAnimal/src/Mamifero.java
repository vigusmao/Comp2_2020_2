public class Mamifero extends Animal {

    public Mamifero(String especie) {
        super(especie, "Mamífero");
    }

    @Override
    public void comer() {
        System.out.println("Bebendo leite...");
    }

    public void emitirSom() {
        System.out.println("Emitindo algum som genérico de mamífero...");
    }


}
