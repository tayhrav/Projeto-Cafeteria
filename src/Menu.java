import java.util.*;

public class Menu {
  private List<Bebida> bebidas;
  private List<Lanche> lanches;

  public Menu() {
    this.bebidas = new ArrayList<>();
    this.lanches = new ArrayList<>();
  }

  public int getProdutoIndex(String nome) {
    for (int i = 0; i < this.bebidas.size(); i++) {
      Bebida bebida = this.bebidas.get(i);
      if (bebida.getNome().equalsIgnoreCase(nome)) {
        return i;
      }
    }

    for (int i = 0; i < this.lanches.size(); i++) {
      Lanche lanche = this.lanches.get(i);
      if (lanche.getNome().equalsIgnoreCase(nome)) {
        return i + this.bebidas.size(); 
      }
    }

    return -1; 
  }

  public Produto getProduto(int index) {
    List<Bebida> bebidas = this.getBebidas();
    List<Lanche> lanches = this.getLanches();

    if (index >= 0 && index < bebidas.size()) {
      return bebidas.get(index);
    } else if (index >= bebidas.size() && index < bebidas.size() + lanches.size()) {
      return lanches.get(index - bebidas.size());
    } else {
      return null;
    }
  }

  public void removerProduto(String nome) {
    int index = getProdutoIndex(nome);
    if (index != -1) {
      if (index < this.bebidas.size()) {
        this.bebidas.remove(index);
      } else {
        index -= this.bebidas.size();
        this.lanches.remove(index);
      }
    }
  }

  public void adicionarBebida(Bebida bebida, double precoPequeno, double precoMedio, double precoGrande) {
    bebida.adicionarPreco(Tamanho.PEQUENO, precoPequeno);
    bebida.adicionarPreco(Tamanho.MEDIO, precoMedio);
    bebida.adicionarPreco(Tamanho.GRANDE, precoGrande);
    this.bebidas.add(bebida);
  }

  public void adicionarLanche(Lanche lanche) {
    this.lanches.add(lanche);
  }

  public List<Bebida> getBebidas() {
    return this.bebidas;
  }

  public List<Lanche> getLanches() {
    return this.lanches;
  }

  public void exibirMenu() {
    System.out.println("Bebidas:");
    for (Bebida bebida : this.bebidas) {
      System.out.println(bebida.getNome());
      for (Tamanho tamanho : Tamanho.values()) {
        System.out.println("  " + tamanho + ": R$" + String.format("%.2f", bebida.getPreco(tamanho)));
      }
    }

    System.out.println("Lanches:");
    for (Lanche lanche : this.lanches) {
      System.out.println(lanche);
    }
  }
}
