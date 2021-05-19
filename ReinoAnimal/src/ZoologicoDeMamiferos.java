import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZoologicoDeMamiferos {

    private static Random random = new Random();

    private List<Mamifero> bichos;

    public ZoologicoDeMamiferos() {
        this.bichos = new ArrayList<>();
    }

    public Mamifero sortearBicho() {
        int posicao = random.nextInt(this.bichos.size());
        return this.bichos.get(posicao);
    }

    public void incluirAnimal(Mamifero bicho) {
        this.bichos.add(bicho);
    }

    public Cachorro escolherCachorro(String raca) {
        switch (raca.toLowerCase()) {
            case "pastor":
            case "pastor alemao":
                return new PastorAlemao();
            case "poodle":
                return new Poodle();
            default:
                return null;
//                return new ViraLata();
        }
    }


}
