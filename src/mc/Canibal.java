package mc;

public class Canibal {
    String nome;

    public Canibal() {
        this.nome = "Canibal";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
