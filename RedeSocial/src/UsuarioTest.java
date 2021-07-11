import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class UsuarioTest {

    private CalculadorIntersecao calcIngenuo;
    private CalculadorIntersecao calcViaBuscaBinaria;
    private CalculadorIntersecao calcEsperto;

    private Random random = new Random();

    @Before
    public void setUp() {
        this.calcIngenuo = new CalculadorIntersecaoIngenuo();
        this.calcViaBuscaBinaria = new CalculadorIntersecaoViaBuscaBinaria();
        this.calcEsperto = new CalculadorIntersecaoEsperto();
    }

    @Test
    public void testarIntersecao() {
        Usuario usuario1 = new Usuario(1);
        Usuario usuario2 = new Usuario(1);

        usuario1.setCalculadorIntersecao(new CalculadorIntersecaoEsperto());

        Usuario amigo1 = new Usuario(101);
        Usuario amigo2 = new Usuario(102);
        Usuario amigo3 = new Usuario(103);
        Usuario amigo4 = new Usuario(104);

        usuario1.adicionarAmigo(amigo1);
        usuario1.adicionarAmigo(amigo2);
        usuario1.adicionarAmigo(amigo3);

        usuario2.adicionarAmigo(amigo1);
        usuario2.adicionarAmigo(amigo3);
        usuario2.adicionarAmigo(amigo4);

        int amigosEmComum = usuario1.obterQuantidadeDeAmigosEmComum(usuario2);
        assertEquals(2, amigosEmComum);
    }

    @Test
    public void testarPerformance() {

        final int TAMANHO = 1_600_000;

        List<Usuario> amigos1 = new ArrayList<>();
        List<Usuario> amigos2 = new ArrayList<>();

        Set<Integer> idsUtilizados1 = new HashSet<>();
        Set<Integer> idsUtilizados2 = new HashSet<>();

        for (int i = 1; i <= TAMANHO; i++) {
            final int id1 = random.nextInt(10 * TAMANHO) + 1;
            if (!idsUtilizados1.contains(id1)) {
                Usuario amigo1 = new Usuario(id1);
                amigos1.add(amigo1);
                idsUtilizados1.add(id1);
            }
            final int id2 = random.nextInt(10 * TAMANHO) + 1;
            if (!idsUtilizados2.contains(id2)) {
                Usuario amigo2 = new Usuario(id2);
                amigos2.add(amigo2);
                idsUtilizados2.add(id2);
            }
        }

        Usuario usuario1 = new Usuario(-1);
        Usuario usuario2 = new Usuario(-2);
        for (Usuario amigo : amigos1) {
            usuario1.adicionarAmigo(amigo);
        }
        for (Usuario amigo : amigos2) {
            usuario2.adicionarAmigo(amigo);
        }

//        int resultado = rodarTesteDePerformance(calcIngenuo, amigos1, amigos2);


        rodarTesteDePerformance(calcViaBuscaBinaria, usuario1, usuario2);
        rodarTesteDePerformance(calcEsperto, usuario1, usuario2);

    }

    private void rodarTesteDePerformance(CalculadorIntersecao calculador,
                                        Usuario usuario1, Usuario usuario2) {

        usuario1.setCalculadorIntersecao(calculador);

        long inicio = System.currentTimeMillis();
        int resultado = usuario1.obterQuantidadeDeAmigosEmComum(usuario2);
        long duracao = System.currentTimeMillis() - inicio;
        System.out.println(String.format(
                "%s --> interseção: %d duração: %.3fs",
                calculador.getClass().getName(), resultado, duracao / 1000f));
    }
}