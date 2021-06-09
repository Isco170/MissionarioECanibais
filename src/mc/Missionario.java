package mc;

public class Missionario {
    String nome;

    public Missionario() {
        this.nome = "Missionario";
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
