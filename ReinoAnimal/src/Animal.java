import java.util.Date;

public abstract class Animal {

    private String especie;

    private String filo;

    private String genero;

    private String classe;

    private Date dataDeNascimento;

    public Animal(String especie, String classe) {
        this.especie = especie;
        this.classe = classe;
        nascer();
    }

    public void dormir() {
        System.out.println("Dormindo...");
    }

    public void mover(int velocidadeEmMilimetrosPorSegundo) {
        System.out.println("Movendo-se a "
                + velocidadeEmMilimetrosPorSegundo
                + " mm/s...");
    }

    public void comer() {
        System.out.println("Comendo...");
    }

    public String getEspecie() {
        return especie;
    }

    protected void nascer() {
        this.dataDeNascimento = new Date();
        System.out.println("Nasci em " + this.dataDeNascimento);
    }

    public abstract void reproduzir();
}
