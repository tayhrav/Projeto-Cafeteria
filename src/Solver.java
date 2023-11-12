import java.util.*;

public class Solver {
    public static void main(String[] arg) {
        Cafeteria caf = new Cafeteria();

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            try {
                if (args[0].equals("end")) {
                    break;
                } else {
                    switch (args[0]) {
                        case "addBebida":
                            caf.getMenu().adicionarBebida(new Bebida(args[1]), number(args[2]), number(args[3]),
                                    number(args[4]));
                            break;
                        case "addLanche":
                            caf.getMenu().adicionarLanche(new Lanche(args[1], number(args[2])));
                            break;
                        case "rm":
                            caf.getMenu().removerProduto(args[1]);
                            break;
                        case "showMenu":
                            caf.exibirMenu();
                            break;
                        case "showPedidos":
                            caf.exibirPedidos();
                            break;
                        case "addPedido":
                            Pedido pedido = new Pedido();
                            Produto produto = caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(args[1]));
                            if (produto instanceof Bebida) {
                                Tamanho tamanho = Tamanho.obterTamanho(args[3]);
                                Bebida bebida = (Bebida) produto;
                                Bebida novaBebida = new Bebida(bebida.getNome());
                                // novaBebida.adicionarPreco(Tamanho.PEQUENO, bebida.getPreco(Tamanho.PEQUENO));
                                // novaBebida.adicionarPreco(Tamanho.MEDIO, bebida.getPreco(Tamanho.MEDIO));
                                // novaBebida.adicionarPreco(Tamanho.GRANDE, bebida.getPreco(Tamanho.GRANDE));
                                novaBebida.adicionarPreco(tamanho, bebida.getPreco(tamanho));
                                novaBebida.setTamanho(tamanho);

                                produto = novaBebida;
                            }

                            ItemPedido item = new ItemPedido(produto, Integer.parseInt(args[2]));
                            pedido.adicionarItem(item);
                            caf.realizarPedido(pedido);
                            break;
                        default:
                            println("fail: comando invalido");
                            break;
                    }
                }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static double number(String value) {
        return Double.parseDouble(value);
    }

    public static void println(Object value) {
        System.out.println(value);
    }

    public static void print(Object value) {
        System.out.print(value);
    }
}

// import java.text.DecimalFormat;
// import java.util.*;

// public class Solver {
// public static void main(String[] arg) {
// Cafeteria caf = new Cafeteria();

// while (true) {
// String line = input();
// println("$" + line);
// String[] args = line.split(" ");

// try {
// if (args[0].equals("end")) {
// break;
// } else if (args[0].equals("addBebida")) {
// caf.getMenu().adicionarBebida(new Bebida(args[1]), number(args[2]),
// number(args[3]),
// number(args[4]));
// } else if (args[0].equals("addLanche")) {
// caf.getMenu().adicionarLanche(new Lanche(args[1], number(args[2])));
// } else if (args[0].equals("rm")) {
// caf.getMenu().removerProduto(args[1]);
// } else if (args[0].equals("showMenu")) {
// caf.exibirMenu();
// } else if (args[0].equals("showPedidos")) {
// caf.exibirPedidos();
// } else if (args[0].equals("addPedido")) {
// Pedido pedido = new Pedido();
// Produto produto =
// caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(args[1]));
// if (produto instanceof Bebida) {
// Tamanho tamanho = Tamanho.obterTamanho(args[3]);
// Bebida bebida = (Bebida) produto;
// Bebida novaBebida = new Bebida(bebida.getNome());
// novaBebida.adicionarPreco(Tamanho.PEQUENO, bebida.getPreco(Tamanho.PEQUENO));
// novaBebida.adicionarPreco(Tamanho.MEDIO, bebida.getPreco(Tamanho.MEDIO));
// novaBebida.adicionarPreco(Tamanho.GRANDE, bebida.getPreco(Tamanho.GRANDE));
// novaBebida.setTamanho(tamanho);

// produto = novaBebida;
// }

// ItemPedido item = new ItemPedido(produto, Integer.parseInt(args[2]));
// pedido.adicionarItem(item);
// caf.realizarPedido(pedido);
// } else {
// println("fail: comando invalido");
// }
// } catch (Exception e) {
// println(e.getMessage());
// }
// }
// }

// private static Scanner scanner = new Scanner(System.in);

// private static String input() {
// return scanner.nextLine();
// }

// private static double number(String value) {
// return Double.parseDouble(value);
// }

// public static void println(Object value) {
// System.out.println(value);
// }

// public static void print(Object value) {
// System.out.print(value);
// }

// public static DecimalFormat form = new DecimalFormat("0.00");
// }
