public class DreDesconhecidoException extends Exception {

    private long dreDesconhecido;

    public DreDesconhecidoException(long dreDesconhecido) {
        this.dreDesconhecido = dreDesconhecido;
    }

    public long getDreDesconhecido() {
        return this.dreDesconhecido;
    }
}
