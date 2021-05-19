import java.util.HashMap;
import java.util.Map;

public class EncontraCaracterMaisFrequente {

    public void encontrarCaracterMaisFrequenteIngenuo(String string) {

        int maxCont = 0;
        char charMaisFrequente = 0;

        for (int i = 0; i < string.length(); i++) {
            char caracter = string.charAt(i);

            int contador = 1;
            for (int j = i + 1; j < string.length(); j++) {
                if (string.charAt(j) == caracter) {
                    contador++;
                }
            }

            if (contador > maxCont) {
                maxCont = contador;
                charMaisFrequente = caracter;
            }
        }

        System.out.println("Caracter mais frequente = " + charMaisFrequente +
                " com " + maxCont + " ocorrências");
    }

    public void encontrarCaracterMaisFrequenteEsperto(String string) {
        int maxCont = 0;
        char charMaisFrequente = 0;

        Map<Character, Integer> contadorByCaracter = new HashMap<>();

        for (int i = 0; i < string.length(); i++) {
            char caracter = string.charAt(i);
            Integer contPreExistente = contadorByCaracter.getOrDefault(caracter, 0);
            final int novaContagem = contPreExistente + 1;
            contadorByCaracter.put(caracter, novaContagem);
            if (novaContagem > maxCont) {
                maxCont = novaContagem;
                charMaisFrequente = caracter;
            }
        }

        System.out.println("Caracter mais frequente = " + charMaisFrequente +
                " com " + maxCont + " ocorrências");
    }
}
