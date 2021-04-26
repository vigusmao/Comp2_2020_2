public class Livro extends Produto {

    private String nome;
    private String editora;

    public Livro(String nome, String editora) {
        super(formatarDescricaoLivro(nome, editora));
        this.nome = nome;
        this.editora = editora;
    }

    private int numeroDePaginas() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    public String getTrechoEmDestaque() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public String getAutor() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public int getAnoDePublicacao() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    private static String formatarDescricaoLivro(String nome, String editora) {
        return String.format("Livro: %s (editora: %s)", nome, editora);
    }

    @Override
    public String toString() {
        return "Livro: " + this.nome;
    }
}
