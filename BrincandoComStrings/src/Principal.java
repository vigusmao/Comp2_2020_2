public class Principal {

    public static void main(String[] args) {

        String x = new String("abc");
        String y = new String("abc");

        System.out.println("x.equals(y) ---> " + (x.equals(y)));
        System.out.println("x == y ---> " + (x == y));

        String z = "abc";
        String w = "abc";

        System.out.println("z.equals(w) ---> " + (z.equals(w)));
        System.out.println("z == w ---> " + (z == w));

        String todasMaiusculas = z.toUpperCase();

        System.out.println(z);
        System.out.println(todasMaiusculas);

    }
}
