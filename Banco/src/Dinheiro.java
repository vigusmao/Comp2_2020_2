public enum Dinheiro {

    NOTA_DE_DUZENTOS_REAIS(200),
    NOTA_DE_CEM_REAIS(100),
    NOTA_DE_CINQUENTA_REAIS(50),
    NOTA_DE_VINTE_REAIS(20),
    NOTA_DE_DEZ_REAIS(10),
    NOTA_DE_CINCO_REAIS(5),
    NOTA_DE_DOIS_REAIS(2),
    MOEDA_DE_UM_REAL(1),
    MOEDA_DE_CINQUENTA_CENTAVOS(0.5),
    MOEDA_DE_VINTE_E_CINCO_CENTAVOS(0.25),
    MOEDA_DE_DEZ_CENTAVOS(0.1),
    MOEDA_DE_CINCO_CENTAVOS(0.05),
    MOEDA_DE_UM_CENTAVO(0.01, "Moedinha de um centavo");

    Dinheiro(double valorMonetario) {
        this(valorMonetario,
                valorMonetario < 1
                        ? String.format("Moeda de %d centavos", (int) (valorMonetario * 100))
                        : (valorMonetario == 1
                                ? "Moeda de um real"
                                : String.format("Nota de %d reais", (int) valorMonetario)));
    }

    Dinheiro(double valorMonetario, String apelido) {  // private por default
        this.valorMonetario = valorMonetario;
        this.apelido = apelido;
    }

    private double valorMonetario;

    private String apelido;

    public String getApelido() {
        return apelido;
    }

    public double getValorMonetario() {
        return this.valorMonetario;
    }


}
