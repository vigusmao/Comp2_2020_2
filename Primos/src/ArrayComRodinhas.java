import java.util.Arrays;

public class ArrayComRodinhas {

    int[] arrayInterno;

    private int quantElementos;


    public ArrayComRodinhas() {
        this.arrayInterno = new int[1];
        this.quantElementos = 0;
    }

    public void add(int elemento) {
        if (this.quantElementos == this.arrayInterno.length) {
            redimensionarArrayInterno();
        }

        this.arrayInterno[this.quantElementos++] = elemento;
    }

    public int get(int posicao) {
        if (posicao < 0 || posicao >= this.quantElementos) {
            throw new ArrayIndexOutOfBoundsException(posicao);
        }
        return this.arrayInterno[posicao];
    }

    private void redimensionarArrayInterno() {
        int[] novoArray = new int[this.arrayInterno.length * 2];  // cresce em PG
        for (int i = 0; i < quantElementos; i++) {
            novoArray[i] = this.arrayInterno[i];
        }
        this.arrayInterno = novoArray;
    }

    public int getQuantElementos() {
        return this.quantElementos;
    }

    public String toString() {
        return Arrays.toString(this.arrayInterno);
    }

}
