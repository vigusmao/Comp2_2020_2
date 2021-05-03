public class ServicoEntregas extends Servico implements Transportador {

    @Override
    public void transportar(Transportavel transportavel, String endereco) {
        System.out.println("Transportando via Serviço de Entregas o produto " +
                transportavel.getDescricao() + "...");
    }
}
