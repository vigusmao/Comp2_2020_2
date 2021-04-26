public class Produto {

    private String descricao;

    private int quantidadeEmEstoque = -1;  // indica que o produto não é vendida

    private float precoEmReais;

    public Produto(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;
    }

    public int getPesoEmGramas() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public Dimensoes getDimensoes() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public float precoEmReais() {
        return this.precoEmReais;
    }

    public float getPrecoEmReais() {
        return this.precoEmReais;
    }

    public void setPrecoEmReais(float preco) {
        this.precoEmReais = preco;
    }

    public String getUrlDaImagem() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    int getQuantidadeEmEstoque() {
        return this.quantidadeEmEstoque;
    }

    void setQuantidadeEmEstoque(int novaQuantidade) {
        this.quantidadeEmEstoque = novaQuantidade;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
