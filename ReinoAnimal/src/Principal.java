public class Principal {

    public static void main(String[] args) {

        ZoologicoDeMamiferos zoo = new ZoologicoDeMamiferos();

        PastorAlemao caozinho1 = (PastorAlemao) zoo.escolherCachorro("pastor");
//        Cachorro caozinho1 = new PastorAlemao();
        Cachorro caozinho2 = zoo.escolherCachorro(null);
        Mamifero mamiferoSorteado = zoo.sortearBicho();

        // vou pedir coisas que qualquer cahorro saberia fazer...
        caozinho1.pular();
        caozinho1.roerOsso();

        mamiferoSorteado.emitirSom();

        // agora algo específico de pastor alemão...
        caozinho1.morderOInvasor();

        Morcego morcego1 = new Morcego();
        Zebra zebra1 = new Zebra();

//        Animal animalEstranho = new Animal(
//                "Ornitorrinco da Montanha", "Nem Ele Sabe");   // daria erro; não posso instanciar classe abstrata!!!

//        Mamifero mamiferoEstranho = new Mamifero("Cavalo Voador");   // daria erro; não posso instanciar classe abstrata!!!

        zoo.incluirAnimal(caozinho1);
        zoo.incluirAnimal(caozinho2);
        zoo.incluirAnimal(morcego1);
        zoo.incluirAnimal(zebra1);

//        zoo.incluirAnimal(animalEstranho);  // não seria aceito!

        for (int i = 0; i < 10; i++) {
            Mamifero mamiferoSorteado = zoo.sortearBicho();
            System.out.println("\n" + mamiferoSorteado.getEspecie());
            mamiferoSorteado.emitirSom();
//            mamiferoSorteado.latir(80);  // não seria aceito, porque alguns Mamiferos não sabem latir!
        }

    }
}
