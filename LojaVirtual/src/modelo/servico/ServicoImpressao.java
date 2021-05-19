package modelo.servico;

import util.Vendavel;

public class ServicoImpressao extends Servico implements Vendavel {

    @Override
    public String getDescricao() {
        return "Serviço de Impressão a Laser...";
    }

    @Override
    public String getUrlDaImagem() {
        return null;
    }
}
