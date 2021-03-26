import java.util.Scanner;

public class Armarios {

    /**
     * Resolve o problema dos armários para a quantidade informada.
     *
     * @param quantArmarios a quantidade de armários (e de crianças)
     * @return A quantidade de armários que estará aberta no final
     */
    public static int resolverProblemaArmarios(int quantArmarios) {
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
        for (int armario = 1; armario <= quantArmarios; armario++) {
            if (armarios[armario]) {
                contAbertos++;
                System.out.println(armario);
            }
        }

        return contAbertos;
    }




    public static void main(String[] args) {

        System.out.println("Digite a quantidade de armários: ");

        Scanner meuScanner = new Scanner(System.in);

        int quantArmarios = meuScanner.nextInt();

        int quantArmariosAbertos = resolverProblemaArmarios(quantArmarios);

        System.out.printf(
                "%d armários estarão abertos no final, dos %d existentes.\n",
                quantArmariosAbertos, quantArmarios);
    }
}
