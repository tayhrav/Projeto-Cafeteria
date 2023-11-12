import java.util.HashMap;
import java.util.Map;

public class Bebida extends Produto {
    private Tamanho tamanho;
    private Map<Tamanho, Double> precos;

    public Bebida(String nome) {
        super(nome, 0); // O preço inicial é 0
        this.precos = new HashMap<>();
    }

    public Tamanho getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(Tamanho tamanho){
        this.tamanho = tamanho;
    }

    public double getPreco(Tamanho tamanho) {
        return this.precos.getOrDefault(tamanho, 0.0); // Retorna 0 se o tamanho não existir
    }

    public void adicionarPreco(Tamanho tamanho, double preco) {
        this.precos.put(tamanho, preco);
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.tamanho + " - R$" + String.format("%.2f", getPreco(getTamanho()));
    }
}
