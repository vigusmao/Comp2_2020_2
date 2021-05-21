public class ColecionavelFactory {

    public static Colecionavel criarColecionavel(
            String tipo, int posicao, String urlDaImagem) {

        switch (tipo.toLowerCase()) {
            case "figurinha":
                return new Figurinha(posicao, urlDaImagem);
            case "selo":
                return new Selo(posicao, urlDaImagem, 0);
            default:
                return null;
        }
    }
}
