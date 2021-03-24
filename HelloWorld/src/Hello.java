import java.util.Scanner;

public class Hello {

    public static void main(String[] args) {

        System.out.println("Hello world!!!");

        Scanner meuScanner = new Scanner(System.in);

        String nome;
        nome = meuScanner.nextLine();

        int x = meuScanner.nextInt();

        System.out.printf(
                "Bom dia, %s, seu nome possui %d caracteres, e o int digitado foi %d.",
                nome, nome.length(), x);
    }
}
