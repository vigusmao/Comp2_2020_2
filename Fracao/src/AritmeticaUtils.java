public class AritmeticaUtils {

    public static int mdc(int x, int y) {

        if (x < 1 || y < 1) {
            throw new ArithmeticException("Os parâmetros precisam ser positivos!");
        }

        int resto = x % y;

        if (resto == 0) {
            return y;
        }

        return mdc(y, resto);
    }
}
