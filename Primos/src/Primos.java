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
            int[] primos = obterPrimos(n);
            System.out.printf("Há %d primos no intervalo [1, %d].\n",
                    primos.length, n);
        }
    }
}
