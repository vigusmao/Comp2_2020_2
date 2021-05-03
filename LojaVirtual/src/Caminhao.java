public class Caminhao extends Produto implements Transportador {

    private String marca;

    public Caminhao(String marca, String modelo, int potenciaEmCv) {
        super("Caminhão da marca " + marca + ", modelo " + modelo);
        this.marca = marca;

    }

    @Override
    public void transportar(Transportavel transportavel, String endereco) {
        System.out.println(
                String.format("Transportando %s para o endereço %s...",
                        transportavel.getDescricao(), endereco));
    }
}
