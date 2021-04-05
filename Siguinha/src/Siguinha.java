public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;


    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

        Aluno fulana = new Aluno(1234, "Fulana de Tal");


        Disciplina algGraf = new Disciplina("Algoritmos e Grafos", 4, "MAB704");
        Disciplina calculo1 = new Disciplina("Calculo 1", 5, "BLAH");
        Periodo periodo20201 = new Periodo(2020, 1);

        fulana.inserirItemHistorico(algGraf, 6f, periodo20201);
        System.out.printf("%.1f\n", fulana.getCra());

        fulana.inserirItemHistorico(calculo1, 4f, periodo20201);
        System.out.printf("%.1f\n", fulana.getCra());

        fulana.inserirItemHistorico(calculo1, 8f, periodo20201);
        System.out.printf("%.1f\n", fulana.getCra());

    }
}
