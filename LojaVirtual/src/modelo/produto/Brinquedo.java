package modelo.produto;

public class Brinquedo extends Produto implements LivroOuBrinquedo {

    public Brinquedo(String descricao) {
        super(descricao);
    }

    public String getMarca() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public int getIdadeMinimaRecomendada() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }
}
