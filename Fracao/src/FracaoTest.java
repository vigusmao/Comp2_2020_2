import org.junit.Test;

import static org.junit.Assert.*;

public class FracaoTest {

    private static final float DELTA = 0.0000001f;

    @Test
    public void testarValorNumerico() {
        assertEquals(0.5, (new Fracao(1, 2)).getValorNumerico(), DELTA);
        assertEquals(0.5, (new Fracao(-1, -2)).getValorNumerico(), DELTA);
        assertEquals(-0.5, (new Fracao(-1, 2)).getValorNumerico(), DELTA);
        assertEquals(-0.5, (new Fracao(1, -2)).getValorNumerico(), DELTA);
        assertEquals(0, (new Fracao(0, 2)).getValorNumerico(), DELTA);
    }

    @Test
    public void testarSinal() {
        assertTrue((new Fracao(1, 2)).getSinal());
        assertTrue((new Fracao(-1, -2)).getSinal());
        assertFalse((new Fracao(1, -2)).getSinal());
        assertFalse((new Fracao(-1, 2)).getSinal());
        assertTrue((new Fracao(0, 2)).getSinal());
    }

    @Test
    public void testarNumerador() {
        assertEquals(1, (new Fracao(1, 3)).getNumerador());
        assertEquals(1, (new Fracao(-1, 3)).getNumerador());
        assertEquals(5, (new Fracao(-5, -10)).getNumerador());
    }

    @Test
    public void testarFracaoNula() {
        assertTrue((new Fracao(0, 1212277)).getSinal());
        assertTrue((new Fracao(0, -1212277)).getSinal());
    }

    @Test
    public void testarToString() {
        assertEquals("2/6", (new Fracao(2, 6)).toString());
        assertEquals("1/3", (new Fracao(-1, -3)).toString());
        assertEquals("-1/3", (new Fracao(-1, 3)).toString());
        assertEquals("-1/3", (new Fracao(1, -3)).toString());
        assertEquals("0", (new Fracao(0, 3)).toString());
        assertEquals("0", (new Fracao(0, -3)).toString());
        assertEquals("7", (new Fracao(7, 1)).toString());
        assertEquals("-7", (new Fracao(-7, 1)).toString());
        assertEquals("5/5", (new Fracao(5, 5)).toString());
    }

    @Test
    public void testarFracaoGeratrizParaFracaoRedutivel() {

        Fracao f = new Fracao(2, 4);
        Fracao fracaoGeratrizEsperada = new Fracao(1, 2);

        Fracao fracaoGeratrizObtida = f.getFracaoGeratriz();

        assertTrue(fracaoGeratrizEsperada.getNumerador() == fracaoGeratrizObtida.getNumerador() &&
                fracaoGeratrizEsperada.getDenominador() == fracaoGeratrizObtida.getDenominador() &&
                fracaoGeratrizEsperada.getSinal() == fracaoGeratrizObtida.getSinal());
    }

    @Test
    public void testarFracaoGeratrizParaFracaoIrredutivel() {

        Fracao f = new Fracao(1, 7);

        Fracao fracaoGeratrizObtida = f.getFracaoGeratriz();

        assertTrue(fracaoGeratrizObtida == f);  // quero verificar que são de fato o mesmo objeto
    }

    @Test
    public void testarIgualdadeSemantica() {
        assertEquals(new Fracao(2, 10), new Fracao(-1, -5));
    }
}