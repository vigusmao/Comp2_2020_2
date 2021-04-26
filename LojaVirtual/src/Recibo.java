public class Recibo {

    private float valorTotal;
    private Usuario usuario;
    private Produto produto;
    private int quantidade;

    public Recibo(float valorTotal, Usuario usuario, Produto produto, int quantidade) {
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public float getValorTotalDaCompra() {
        return this.valorTotal;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    public String toString() {
        return String.format("Recibo no valor de R$%.2f para %s " +
                "referente à compra de %d unidades de %s",
                this.valorTotal, usuario.getNome(), this.quantidade,
                this.produto.toString());


    }
}
