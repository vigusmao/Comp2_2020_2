public class Inseto extends Animal {

    public Inseto() {
        super("Inseto", "Inseto genérico");
    }

    @Override
    public void reproduzir() {
        System.out.println("Vou reproduzir colocando ovinhos...");
    }
}
