public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;


    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

        Aluno joao = new Aluno(1111, new String("João"));
        Aluno maria = new Aluno(2222, "Maria");

        System.out.println("joao == maria? " + (joao == maria));
        System.out.println("joao == joao? " + (joao == joao));

        System.out.println("joao.equals(maria)? " + joao.equals(maria));
        System.out.println("joao.equals(joao)? " + joao.equals(joao));

        Aluno outroObjetoRepresentandoJoao = new Aluno(1111, new String("João"));

        System.out.println("joao == outroObjetoRepresentandoJoao? " +
                (joao == outroObjetoRepresentandoJoao));

        System.out.println("joao.equals(outroObjetoRepresentandoJoao)? " +
                (joao.equals(outroObjetoRepresentandoJoao)));

//        Aluno joao2 = new Aluno(1111, "João");
        Aluno joao2;
        joao2 = joao;

        System.out.println("joao2 == joao? " +
                (joao2 == joao));
        System.out.println("joao2.equals(joao)? " +
                (joao2.equals(joao)));



    }
}
