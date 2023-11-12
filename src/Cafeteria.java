import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private Menu menu;
    private List<Pedido> pedidos;

    public Cafeteria() {
        this.menu = new Menu();
        this.pedidos = new ArrayList<>();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void realizarPedido(Pedido pedido) {
        this.pedidos.add(pedido); 
    }

    public void exibirMenu() {
        this.menu.exibirMenu();
    }

    public void exibirPedidos() {
        for (Pedido pedido : this.pedidos) {
            pedido.exibirPedido();
            System.out.println();
        }
    }
}
