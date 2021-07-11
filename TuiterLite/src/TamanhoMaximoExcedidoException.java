public class TamanhoMaximoExcedidoException extends Exception {

    private int caracteresEmExcesso;

    TamanhoMaximoExcedidoException(int caracteresEmExcesso) {
        this.caracteresEmExcesso = caracteresEmExcesso;
    }

    public int getCaracteresEmExcesso() {
        return this.caracteresEmExcesso;
    }
}
