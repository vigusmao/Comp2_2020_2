import java.util.Random;

public class Principal {

    public static void main(String[] args) {

        Random random = new Random();

        ArrayComRodinhas meuArray = new ArrayComRodinhas();

        int N = 100_000;

        long inicio = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            meuArray.add(random.nextInt(100));
        }
        long duracao = System.currentTimeMillis() - inicio;

        System.out.printf("duracao = %.3f\n", duracao / 1000f);

        System.out.printf("O array agora tem %d elementos (o tamanho físico é %d).",
                meuArray.getQuantElementos(), meuArray.arrayInterno.length);

    }
}
