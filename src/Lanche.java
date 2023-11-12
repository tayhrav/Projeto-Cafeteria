public class Lanche extends Produto {
    // private Tamanho tamanho;
    // private TipoLanche tipoLanche;

    public Lanche(String nome, double preco){
        super(nome, preco);
        // this.tamanho = tamanho;
    }

    // public Tamanho getTamanho(){
    //     return this.tamanho;
    // }

    // public TipoLanche tipoLanche(){
    //     return this.tipoLanche;
    // }

    // @Override
    // public String toString(){
    //     return this.nome + " - " + this.tamanho + " - R$" + this.preco;
    // }

    @Override
    public String toString(){
        return this.nome + ": R$" + String.format("%.2f", this.preco);
    }
}
