/**
 * Eistem n armários numerados de 1 a n, e n crianças. Os armários estão inicialmente fechados.
 * Cada criança, uma por vez, muda o estado (se está fechado, abre; se está aberto, fecha)
 * de cada armário cujo número seja múltiplo do número da própria criança.
 *
 * Ex.: A terceira criança muda o estado dos armários 3, 6, 9, etc.
 *
 * Pergunta: que armários estarão abertos após a passagem da n-ésima criança?
 */
public class Armarios {

    /**
     * Resolve o problema dos armários para a quantidade informada.
     *
     * @param quantArmarios a quantidade de armários (e de crianças)
     * @return A quantidade de armários que estará aberta no final
     */
    public static int resolverProblemaArmariosQuadratico(int quantArmarios) {
        boolean[] armarios;   // false: fechado; true: aberto

        armarios = new boolean[quantArmarios + 1];  // descartaremos a posição 0

        for (int crianca = 1; crianca <= quantArmarios; crianca++) {
            for (int armario = 1; armario <= quantArmarios; armario++) {
                if (armario % crianca == 0) {
                    armarios[armario] = !armarios[armario];
                }
            }
        }

        int contAbertos = 0;
        int armario = 1;
        while (armario <= quantArmarios) {
            if (armarios[armario]) {
                contAbertos++;
//                System.out.println(armario);
            }
            armario++;
        }

        return contAbertos;
    }

    /**
     * Resolve o problema dos armários para a quantidade informada.
     *
     * @param quantArmarios a quantidade de armários (e de crianças)
     * @return A quantidade de armários que estará aberta no final
     */
    public static int resolverProblemaArmariosNLogN(int quantArmarios) {
        boolean[] armarios;   // false: fechado; true: aberto

        armarios = new boolean[quantArmarios + 1];  // descartaremos a posição 0

        for (int crianca = 1; crianca <= quantArmarios; crianca++) {
            for (int armario = crianca; armario <= quantArmarios; armario += crianca) {
                armarios[armario] = !armarios[armario];
            }
        }

        int contAbertos = 0;
        int armario = 1;
        while (armario <= quantArmarios) {
            if (armarios[armario]) {
                contAbertos++;
//                System.out.println(armario);
            }
            armario++;
        }

        return contAbertos;
    }

    public static int resolverProblemaArmariosLinear(int quantArmarios) {
        int cont = 0;

        for (int i = 1; i <= quantArmarios; i++) {
            int raiz = (int) Math.sqrt(i);
            if (raiz * raiz == i) {
                cont++;
//                System.out.println(i);
            }
        }

        return cont;
    }

    public static int resolverProblemaArmariosSqrtN(long quantArmarios) {
        int cont = 0;
        long base = 1;
        long armario = 1;

        while (armario <= quantArmarios) {
            cont++;
//            System.out.println(armario);
            base++;
            armario = base * base;
        }

        return cont;
    }

    public static int resolverProblemaArmariosConstante(long quantArmarios) {
        return (int) Math.sqrt(quantArmarios);
    }


    public static void main(String[] args) {

        long quantArmarios = 5_120_000_000L;

        long inicio = System.currentTimeMillis();
        int quantArmariosAbertos = resolverProblemaArmariosConstante(quantArmarios);
        long duracao = System.currentTimeMillis() - inicio;

        System.out.printf(
                "%d armários estarão abertos no final, dos %d existentes.\n",
                quantArmariosAbertos, quantArmarios);
        System.out.printf("Duração: %.9f segundos", duracao / 1000.0);
    }
}
