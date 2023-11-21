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
    if (index >= 0 && index < this.bebidas.size()) {
      return this.bebidas.get(index);
    } else if (index >= this.bebidas.size() && index < this.bebidas.size() + this.lanches.size()) {
      return this.lanches.get(index - this.bebidas.size());
    } else {
      return null;
    }
  }

  public void removerProduto(String nome) {
    int index = getProdutoIndex(nome);

    if (index < this.bebidas.size()) {
      this.bebidas.remove(index);
    } else {
      index -= this.bebidas.size();
      this.lanches.remove(index);
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

  public void editarProduto(String nome, Map<String, Object> novosValores) {
    int index = getProdutoIndex(nome);

    if (index != -1) {
      Produto produto = getProduto(index);

      if (produto instanceof Bebida) {
        Bebida bebida = (Bebida) produto;
        editarBebida(bebida, novosValores);
      } else {
        Lanche lanche = (Lanche) produto;
        editarLanche(lanche, novosValores);
      }
    }
  }

  private void editarBebida(Bebida bebida, Map<String, Object> novosValores) {
    for (Map.Entry<String, Object> entry : novosValores.entrySet()) {
      String atributo = entry.getKey();
      Object valor = entry.getValue();

      switch (atributo) {
        case "nome":
          bebida.setNome((String) valor);
          break;
        case "precoPequeno":
          bebida.adicionarPreco(Tamanho.PEQUENO, (Double) valor);
          break;
        case "precoMedio":
          bebida.adicionarPreco(Tamanho.MEDIO, (Double) valor);
          break;
        case "precoGrande":
          bebida.adicionarPreco(Tamanho.GRANDE, (Double) valor);
          break;
      }
    }
  }

  private void editarLanche(Lanche lanche, Map<String, Object> novosValores) {
    for (Map.Entry<String, Object> entry : novosValores.entrySet()) {
      String atributo = entry.getKey();
      Object valor = entry.getValue();

      switch (atributo) {
        case "nome":
          lanche.setNome((String) valor);
          break;
        case "preco":
          lanche.setPreco((Double) valor);
          break;
        default:
          System.out.println("Atributo desconhecido.");
      }
    }
  }

  public void exibirMenu() {
    System.out.println("CARDÁPIO");
    if (!this.bebidas.isEmpty()) {
      System.out.println("\u001b[37;1mBebidas:\u001b[37;0m");
      for (Bebida bebida : this.bebidas) {
        System.out.println(bebida.getNome());
        for (Tamanho tamanho : Tamanho.values()) {
          System.out.println("  " + tamanho + ": R$" + String.format("%.2f", bebida.getPreco(tamanho)));
        }
      }
    }

    if (!this.lanches.isEmpty()) {
      System.out.println("\u001b[37;1mLanches:\u001b[37;0m");
      for (Lanche lanche : this.lanches) {
        System.out.println(lanche);
      }
    }
  }

}
