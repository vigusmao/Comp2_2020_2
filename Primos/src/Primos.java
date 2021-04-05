import java.util.Arrays;

public class Primos {

    /**
     * Retorna um array contendo exatamente os números primos em [1, n].
     * @param n O maior número a ser considerado
     * @return um array de inteiros com os primos no intervalo dado.
     */
    public static int[] obterPrimos(int n) {
        ArrayComRodinhas meuArrayComRodinhas = new ArrayComRodinhas();

        for (int x = 2; x <= n; x++) {
            if (ehPrimo(x)) {
                meuArrayComRodinhas.add(x);
            }
        }

        int[] apenasPrimos = new int[meuArrayComRodinhas.getQuantElementos()];
        for (int i = 0; i < meuArrayComRodinhas.getQuantElementos(); i++) {
            apenasPrimos[i] = meuArrayComRodinhas.get(i);
        }

        return apenasPrimos;
    }

    private static boolean ehPrimo(int x) {
        if (x < 2) {
            return false;
        }
        if (x == 2) {
            return true;
        }
        if (x % 2 == 0) {
            return false;
        }

        for (int divisor = 3; divisor * divisor <= x; divisor += 2) {
            if (x % divisor == 0) {
                return false;
            }
        }

        return true;
    }



    public static int[] obterPrimosViaCrivo(int n) {
        // ToDo IMPLEMENT ME!!!

        return null;
    }

    public static void main(String[] args) {

        for (int n = 1; n <= 20; n++) {

            long inicio = System.currentTimeMillis();
            int[] primos = obterPrimos(n);
            long duracao = System.currentTimeMillis() - inicio;

            System.out.printf("Há %d primos no intervalo [1, %d].\n",
                    primos.length, n);

            // Se quiséssemos imprimir o array na tela

            for (int i = 0; i < primos.length; i++) {
                int x = primos[i];
                if (i < primos.length - 1) {
                    System.out.printf("%d, ", x);
                } else {
                    System.out.printf("%d\n", x);
                }
            }
//
//            // ou...
//            for (int x : primos) {  // for each... (para cada elemento de "primos"...)
//                System.out.println(x);
//            }
//
//            // ou...
//            System.out.println(Arrays.toString(primos));
        }
    }
}
