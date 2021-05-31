public class Banco {

    public static final float[] moedasECedulas = new float[] {
            0.05f, 0.1f, 0.25f, 0.5f, 1, 2, 5, 10, 20, 50, 100, 200};

    public void depositarEmEspecie(float[] dinheiros) {
        float totalDepositado = 0;
        for (float dinheiro : dinheiros) {
            if (!ehDinheiroValido(dinheiro)) {
                throw new IllegalArgumentException("Dinheiro inválido!!!");
            }
            totalDepositado += dinheiro;
        }

        // etc.
    }

    /**
     * Retorna um array contendo moedas e cédulas de Real,
     * totalizando o valor desejado.
     *
     * @param valorDesejado
     * @return
     */
    public float[] sacar(float valorDesejado) {
        return null;  // ToDo 
    }

    public static boolean ehDinheiroValido(float valor) {
        for (float valorValido : moedasECedulas) {
            if (valor == valorValido) {
                return true;
            }
        }
        return false;
    }
}
