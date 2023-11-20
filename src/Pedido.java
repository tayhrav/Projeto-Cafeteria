import java.util.*;

public class Pedido {
    private int idPedido;
    private static int nextIdPedido = 0;
    private List<ItemPedido> itens;

    public Pedido() {
        this.idPedido = nextIdPedido++;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public List<ItemPedido> getItens() {
        return this.itens;
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : this.itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void exibirPedido() {
        System.out.println("Pedido #" + this.idPedido);
        for (ItemPedido item : this.itens) {
            System.out.println(item);
        }
        System.out.println("Total do Pedido: R$" + String.format("%.2f", calcularTotal()));
    }
}