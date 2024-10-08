public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double calcularSubtotal() {
        if (produto instanceof Bebida) {
            Bebida bebida = (Bebida) produto;
            return bebida.getPreco(bebida.getTamanho()) * this.quantidade;
        } else {
            return this.produto.getPreco() * this.quantidade;
        }
    }

    @Override
    public String toString() {
        return this.produto + " - Quantidade: " + this.quantidade;
    }
}
