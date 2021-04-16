import java.util.ArrayList;
import java.util.Random;

public class Zoologico {

    private static Random random = new Random();

    private ArrayList<Mamifero> bichos;

    public Zoologico() {
        this.bichos = new ArrayList<>();
    }

    public Mamifero sortearBicho() {
        int posicao = random.nextInt(this.bichos.size());
        return this.bichos.get(posicao);
    }

    public void incluirAnimal(Mamifero bicho) {
        this.bichos.add(bicho);
    }
}
