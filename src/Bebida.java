import java.util.*;

public class Bebida extends Produto {
    private Tamanho tamanho;
    private Map<Tamanho, Double> precos;

    public Bebida(String nome) {
        super(nome, 0);
        this.precos = new HashMap<>();
    }

    public Tamanho getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public double getPreco(Tamanho tamanho) {
        return this.precos.get(tamanho);
    }

    public void adicionarPreco(Tamanho tamanho, double preco) {
        this.precos.put(tamanho, preco);
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.tamanho + " - R$" + String.format("%.2f", getPreco(getTamanho()));
    }
}
