import org.junit.Test;

import static org.junit.Assert.*;

public class AritmeticaUtilsTest {

    @Test
    public void testarMdc() {
        assertEquals(2, AritmeticaUtils.mdc(8, 2));
        assertEquals(2, AritmeticaUtils.mdc(2, 8));
        assertEquals(6, AritmeticaUtils.mdc(12, 18));
    }
}