import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Mamifero extends Animal {

    private List<String> apelidos;





    public Mamifero(String especie) {
        super(especie, "Mamífero");
    }

    @Override
    public void comer() {
        System.out.println("Bebendo leite...");
    }

    /**
     * Emite som, em um volume padrão, durante um tempo padrão.
     */
    public abstract void emitirSom();  // Qualquer mamífero precisa implementar o método emitirSom()

    public void dormir() {
        System.out.println("Zzzzzzzz...");
    }

    @Override
    public void reproduzir() {
        System.out.println("Vou reproduzir de forma sexuada...");
    }

    @Override
    protected void nascer() {
        System.out.println("Vou mamar pela primeira vez...");
    }
}
