import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {

    public void depositarEmEspecie(Dinheiro[] dinheiros) {
        float totalDepositado = 0;
        for (Dinheiro moedaOuCedula : dinheiros) {
            totalDepositado += moedaOuCedula.getValorMonetario();
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
    public Dinheiro[] sacar(float valorDesejado) throws ValorInatingivelException {

        List<Dinheiro> saque = new ArrayList<>();

        float valorAcumulado = 0;
        while (valorAcumulado < valorDesejado) {
            Dinheiro dinheiro = escolherDinheiro(valorDesejado - valorAcumulado);
            if (dinheiro == null) {
                throw new ValorInatingivelException();
            }
            if (dinheiro == Dinheiro.MOEDA_DE_UM_CENTAVO) {
                // faça algo (ex.: faça um som de moedinha caindo no cofrinho)
            }
            saque.add(dinheiro);
            valorAcumulado += dinheiro.getValorMonetario();
        }


        return null;
    }

    private static Dinheiro escolherDinheiro(float valorAlvo) {
        // vou gulosamente escolher o maior valor possível que não exceda
        // o valor alvo

        for (Dinheiro moedaOuCedula : Dinheiro.values()) {
            if (moedaOuCedula.getValorMonetario() <= valorAlvo) {
                return moedaOuCedula;
            }
        }

        return null;  // ToDo talvez lançar exceção
    }

    public static void imprimirCarteira(Dinheiro[] carteira) {

        double total = 0;

        Map<Dinheiro, Integer> contByDinheiro = new HashMap<>();

        for (Dinheiro dinheiro : carteira) {
            total += dinheiro.getValorMonetario();
            final Integer contPreExistente = contByDinheiro.getOrDefault(dinheiro, 0);
            contByDinheiro.put(dinheiro, contPreExistente + 1);
        }

        for (Map.Entry<Dinheiro, Integer> dinheiroEContador : contByDinheiro.entrySet()) {
            Dinheiro dinheiro = dinheiroEContador.getKey();
            int contador = dinheiroEContador.getValue();
            System.out.println(String.format("%s: %d", dinheiro.getApelido(), contador));
        }

        System.out.printf("Total = %.2f\n", total);
    }

    public static void main(String[] args) {
        Dinheiro[] carteira = new Dinheiro[] {
                Dinheiro.MOEDA_DE_CINCO_CENTAVOS, Dinheiro.NOTA_DE_CINQUENTA_REAIS,
                Dinheiro.NOTA_DE_CINCO_REAIS, Dinheiro.NOTA_DE_CINCO_REAIS,
                Dinheiro.NOTA_DE_CINCO_REAIS,
                Dinheiro.MOEDA_DE_UM_CENTAVO
        };

        imprimirCarteira(carteira);
    }
}
