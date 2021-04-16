public class Principal {

    public static void main(String[] args) {

        Zoologico zoo = new Zoologico();

        Cachorro caozinho1 = new Cachorro();
        Cachorro caozinho2 = new Cachorro();
        Morcego morcego1 = new Morcego();
        Zebra zebra1 = new Zebra();

        Animal animalEstranho = new Animal(
                "Ornitorrinco da Montanha", "Nem Ele Sabe");

        zoo.incluirAnimal(caozinho1);
        zoo.incluirAnimal(caozinho2);
        zoo.incluirAnimal(morcego1);
        zoo.incluirAnimal(zebra1);

//        zoo.incluirAnimal(animalEstranho);  // não seria aceito!

        for (int i = 0; i < 10; i++) {
            Mamifero mamiferoSorteado = zoo.sortearBicho();
            System.out.println("\n" + mamiferoSorteado.getEspecie());
            mamiferoSorteado.emitirSom();
        }

//        mamiferoSorteado.latir(80);  // não seria aceito, porque alguns Mamiferos não sabem latir!
    }
}
