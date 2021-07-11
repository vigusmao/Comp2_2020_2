public abstract class Cachorro extends Mamifero {

    public Cachorro() {
//        super();  // o compilador acrescentaria essa linha automaticamente!
        super("Canídeo");
    }

    private String raca;

    public void roerOsso() {
        System.out.println("Roendo osso...");
    }

    public abstract void latir(int volumeEmDecibeis);

    public void pular() {
        System.out.println("Pulando...");
    }

    public String getRaca() {
        return raca;
    }
}