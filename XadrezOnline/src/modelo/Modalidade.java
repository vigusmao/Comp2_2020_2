package modelo;

public enum Modalidade {

    BULLET(1, 0),
    BLITZ(5, 3),
    RAPID(15, 10),
    CLASSICAL(30, 20);

    private int tempoTotalEmMinutos;
    private int incrementoDeTempoPorLanceEmSegundos;

    Modalidade(int tempoTotalEmMinutos, int incrementoDeTempoPorLanceEmSegundos) {
        this.tempoTotalEmMinutos = tempoTotalEmMinutos;
        this.incrementoDeTempoPorLanceEmSegundos = incrementoDeTempoPorLanceEmSegundos;
    }

    public int getTempoTotalEmMinutos() {
        return tempoTotalEmMinutos;
    }

    public int getIncrementoDeTempoPorLanceEmSegundos() {
        return incrementoDeTempoPorLanceEmSegundos;
    }
}
