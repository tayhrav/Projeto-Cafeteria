public class Transacao {
    private Pedido pedido;

    public Transacao(Pedido pedido) {
        this.pedido = pedido;
    }

    public double calcularTotal() {
        return this.pedido.calcularTotal();
    }

    public void exibirTransacao() {
        this.pedido.exibirPedido();
    }
}
