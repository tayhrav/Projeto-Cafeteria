import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cafeteria {
    private Menu menu;
    private List<Pedido> pedidos;
    private List<Transacao> transacoes;

    public Cafeteria() {
        this.menu = new Menu();
        this.pedidos = new ArrayList<>();
        this.transacoes = new ArrayList<>();
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

    public boolean verificaIdPedido(int idPedido) {
        for (Pedido pedido : this.pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return true;
            }
        }

        return false;
    }

    public ItemPedido getItem(String nome) {
        for (Pedido pedido : this.pedidos) {
            for (ItemPedido item : pedido.getItens()) {
                if (item.getProduto().getNome().equals(nome)) {
                    return item;
                }
            }
        }

        return null;
    }

    public Pedido getPedido(int idPedido) {
        boolean valor = verificaIdPedido(idPedido);
        if (valor) {
            for (Pedido pedido : this.pedidos) {
                if (pedido.getIdPedido() == idPedido) {
                    return pedido;
                }
            }
        }

        return null;
    }

    public void finalizarPedido(int idPedido) {
        // for (Pedido pedido : this.pedidos) {
        // if (pedido.getIdPedido() == idPedido) {
        // this.transacoes.add(new Transacao(pedido));
        // this.pedidos.remove(pedido);
        // return;
        // }
        // }

        Pedido pedido = getPedido(idPedido);
        if (pedido != null) {
            this.transacoes.add(new Transacao(pedido));
            this.pedidos.remove(pedido);
        }

    }

    public void visualizarTransacoes() {
        if (this.transacoes.isEmpty()) {
            Solver.println("Não há transações.");
            return;
        }

        Solver.println("TRANSAÇÕES");
        for (Transacao transacao : this.transacoes) {
            transacao.exibirTransacao();
        }

        Solver.println("\nTotal de dinheiro ganho: " + calcularTotalDinheiro());
    }

    public double calcularTotalDinheiro() {
        double total = 0;
        for (Transacao transacao : this.transacoes) {
            total += transacao.calcularTotal();
        }
        return total;
    }

    public void editarPedido(int idPedido, String nome, Map<String, Object> novosValores) {
        for (Map.Entry<String, Object> entry : novosValores.entrySet()) {
            String atributo = entry.getKey();
            Object valor = entry.getValue();

            switch (atributo) {
                case "produto":
                    getItem(nome).setProduto((Produto) valor);
                    break;
                case "quantidade":
                    getItem(nome).setQuantidade((int) valor);
                    break;
            }
        }
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void exibirPedidos() {
        int ctt = 0;
        System.out.println("PEDIDOS");
        for (Pedido pedido : this.pedidos) {
            if (ctt == this.pedidos.size() - 1) {
                pedido.exibirPedido();
                return;
            }
            pedido.exibirPedido();
            System.out.println();
            ctt++;
        }
    }
}
