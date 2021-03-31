import java.util.Arrays;

public class Primos {

    /**
     * Retorna um array contendo exatamente os números primos em [1, n].
     * @param n O maior número a ser considerado
     * @return um array de inteiros com os primos no intervalo dado.
     */
    public static int[] obterPrimos(int n) {
        // ToDo IMPLEMENT ME!!!

        return null;
    }

    public static int[] obterPrimosViaCrivo(int n) {
        // ToDo IMPLEMENT ME!!!

        return null;
    }

    public static void main(String[] args) {

        for (int n = 10; n <= 10_000; n *= 10) {

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
                    System.out.printf("%d", x);
                }
            }

            // ou...
            for (int x : primos) {  // for each... (para cada elemento de "primos"...)
                System.out.println(x);
            }

            // ou...
            System.out.println(Arrays.toString(primos));
        }
    }
}
