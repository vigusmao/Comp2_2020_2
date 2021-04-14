import java.util.Objects;

/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {

    public static final String SEPARADOR = "/";

    private final int numerador;  // sempre não-negativo

    private final int denominador;  // sempre positivo

    private final boolean sinal;  // true, para positiva ou nula; false, para negativa

    private Fracao fracaoGeratriz;

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */
    public Fracao(int numerador, int denominador) {

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }

        this.numerador = Math.abs(numerador);
        this.denominador = numerador == 0 ? 1 : Math.abs(denominador);

        this.sinal = Math.signum(numerador) == Math.signum(denominador) ||
                numerador == 0;
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {
        return sinal;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
        return numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() {
        return denominador;
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
        float valorAbsoluto = numerador / (float) denominador;
        return sinal ? valorAbsoluto : -valorAbsoluto;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {
        if (fracaoGeratriz == null) {
            obterFracaoGeratriz();  // lazy instantiation
        }

        return fracaoGeratriz;
    }

    private void obterFracaoGeratriz() {
        if (numerador == 0) {
            this.fracaoGeratriz = this;
            return;
        }

        int mdc = AritmeticaUtils.mdc(numerador, denominador);

        if (mdc == 1) {
            this.fracaoGeratriz = this;
            return;
        }

        this.fracaoGeratriz = new Fracao(
                numerador / mdc * (sinal ? 1 : -1),
                denominador / mdc);
    }

    @Override
    public String toString() {
        if (numerador == 0) {
            return "0";
        }

        return String.format("%s%d%s",
                !this.sinal ? "-" : "",
                numerador,
                denominador != 1 ? SEPARADOR + denominador : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao outraFracao = (Fracao) o;

        Fracao minhaFracaoGeratriz = getFracaoGeratriz();
        Fracao outraFracaoGeratriz = outraFracao.getFracaoGeratriz();

        return minhaFracaoGeratriz.getNumerador() == outraFracaoGeratriz.getNumerador() &&
                minhaFracaoGeratriz.getDenominador() == outraFracaoGeratriz.getDenominador() &&
                minhaFracaoGeratriz.getSinal() == outraFracaoGeratriz.getSinal();
    }
}
