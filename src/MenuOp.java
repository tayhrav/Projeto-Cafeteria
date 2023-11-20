import java.util.HashMap;
import java.util.Map;

public class MenuOp {
    public static void exibirMenuPrincipal() {
        Solver.println("------- Menu Principal -------");
        Solver.println("Por favor, selecione uma opção:");
        Solver.println("  1. Gerenciar Pedidos");
        Solver.println("  2. Gerenciar Cardápio");
        Solver.println("  3. Visualizar Transações");
        Solver.print("Opção: ");
    }

    public static void gerenciarPedidos(Cafeteria caf) {
        Solver.println("Gerenciamento de pedidos:");
        Solver.println("  1. Adicionar pedido");
        Solver.println("  2. Finalizar pedido");
        Solver.println("  3. Editar pedido");
        Solver.println("  4. Mostrar pedidos");
        Solver.print("Opção: ");
        String opcaoPedidos = Solver.input();
        System.out.println();
        try {
            switch (opcaoPedidos) {
                case "1":
                    adicionarPedido(caf);
                    break;
                case "2":
                    removerPedido(caf);
                    break;
                case "3":
                    editarPedido(caf);
                    break;
                case "4":
                    if (verificarPedidosVazio(caf))
                        return;
                    caf.exibirPedidos();
                    break;
                default:
                    Solver.println("Opção inválida. Tente novamente.");
                    break;
            }
        } catch (Exception e) {
            Solver.println(e.getMessage());
        }
    }

    public static void adicionarPedido(Cafeteria caf) throws Exception {
        if (verificarCardapioVazio(caf))
            return;

        Pedido pedido = new Pedido();

        while (true) {
            try {
                caf.exibirMenu();
                System.out.println();
                Produto produto = null;

                while (true) {
                    try {
                        Solver.println("Adicionar item ao pedido");
                        Solver.print("Nome (ou 's' para encerrar o pedido): ");
                        String nome = Solver.input();

                        if (nome.equalsIgnoreCase("s")) {
                            break;
                        }

                        if (!nome.matches("[a-zA-Z]+")) {
                            throw new Exception("\nO nome do produto deve conter apenas letras.\n");
                        }

                        produto = caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(nome));

                        if (produto == null) {
                            throw new Exception("\nProduto não encontrado no cardápio.\n");
                        }

                        break;

                    } catch (Exception e) {
                        Solver.println(e.getMessage());
                    }
                }

                Tamanho tam = null;
                if (produto instanceof Bebida) {
                    while (true) {
                        Solver.print("Tamanho (p, m ou g): ");
                        tam = Tamanho.obterTamanho(Solver.input());
                        try {
                            if (tam == null) {
                                throw new Exception("\nTamanho não permitido. Tente novamente.\n");
                            }
                        } catch (Exception e) {
                            Solver.println(e.getMessage());
                        }
                        if (tam != null) {
                            break;
                        }
                    }

                    Bebida bebida = (Bebida) produto;
                    Bebida novaBebida = new Bebida(bebida.getNome());
                    novaBebida.adicionarPreco(tam, bebida.getPreco(tam));
                    novaBebida.setTamanho(tam);

                    produto = novaBebida;
                }

                int quant = 0;
                while (true) {
                    try {
                        Solver.print("Quantidade: ");
                        quant = Integer.parseInt(Solver.input());

                        if (quant <= 0) {
                            throw new Exception("\nInforme uma quantidade maior que zero, por favor.\n");
                        }

                        break;
                    } catch (NumberFormatException e) {
                        Solver.println("\nInforme uma quantidade numérica, por favor.\n");
                    } catch (Exception e) {
                        Solver.println(e.getMessage());
                    }
                }

                ItemPedido item = new ItemPedido(produto, quant);

                pedido.adicionarItem(item);

                Solver.println("\nItem adicionado ao pedido com sucesso!\n");

            } catch (Exception e) {
                Solver.println(e.getMessage());
            }

            Solver.print("Deseja adicionar mais algum item ao pedido? (s/n): ");
            String resposta = Solver.input().toLowerCase();
            if (!resposta.equals("s")) {
                break;
            }
        }

        caf.realizarPedido(pedido);
        Solver.println("\nPedido concluído com sucesso!");
    }

    public static void removerPedido(Cafeteria caf) throws Exception {
        if (verificarPedidosVazio(caf))
            return;

        while (true) {
            try {
                Solver.println("Finalizar pedido");
                caf.exibirPedidos();

                Solver.print("\nDigite o ID do pedido que deseja finalizar: ");
                int idPedido = Integer.parseInt(Solver.input());

                if (caf.getIdPedido(idPedido) == -1) {
                    throw new Exception("\nID não encontrado.\n");
                }

                caf.finalizarPedido(idPedido);

                Solver.println("\nPedido finalizado com sucesso!");

                break;
            } catch (NumberFormatException e) {
                Solver.println("\nInforme um inteiro, por favor.\n");
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        }
    }

    public static void editarPedido(Cafeteria caf) {
        if (verificarPedidosVazio(caf))
            return;
        String nomeProduto;
        int idPedido;

        while (true) {
            Solver.println("Editar pedido");
            caf.exibirPedidos();

            Solver.print("\nDigite o ID do pedido que deseja editar: ");
            idPedido = Integer.parseInt(Solver.input());

            try {
                if (caf.getIdPedido(idPedido) == -1) {
                    throw new Exception("\nID não encontrado.\n");
                }
                break;
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        }

        while (true) {
            Solver.println("\nEditar item do pedido");

            Solver.print("Nome do produto: ");
            nomeProduto = Solver.input();

            ItemPedido item = caf.getItem(nomeProduto);

            if (item == null) {
                Solver.println("\nProduto não encontrado no pedido.");
                continue;
            }

            break;
        }

        while (true) {
            Solver.print("Escolha o atributo a ser editado (produto, quantidade): ");
            String atributo = Solver.input().toLowerCase();

            if (!atributo.equals("produto") && !atributo.equals("quantidade")) {
                Solver.println("\nAtributo não encontrado. Tente novamente.\n");
                continue;
            }

            if (atributo.equals("produto")) {
                Map<String, Object> novosValores = new HashMap<>();
                System.out.println();
                caf.exibirMenu();
                Solver.print("\nDigite o nome do novo produto: ");
                String novoNomeProduto = Solver.input();
                Produto novoProduto = caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(novoNomeProduto));

                if (novoProduto == null) {
                    Solver.println("\nProduto não encontrado no cardápio.\n");
                    continue;
                }

                if (novoProduto instanceof Bebida) {
                    Bebida bebida = (Bebida) novoProduto;
                    Solver.print("Digite o novo tamanho (p, m ou g): ");
                    String tam = Solver.input();
                    Tamanho novoTamanho = Tamanho.obterTamanho(tam);
                    bebida.setTamanho(novoTamanho);
                }

                novosValores.put("produto", novoProduto);

                caf.editarPedido(idPedido, nomeProduto, novosValores);

                Solver.println("\nItem editado com sucesso!");

                break;
            } else if (atributo.equals("quantidade")) {
                Solver.print("Digite a nova quantidade: ");
                try {
                    int novaQuantidade = Integer.parseInt(Solver.input());

                    if (novaQuantidade <= 0) {
                        throw new Exception("\nInforme uma quantidade maior que zero, por favor.\n");
                    }

                    Map<String, Object> novosValores = new HashMap<>();
                    novosValores.put("quantidade", novaQuantidade);

                    caf.editarPedido(idPedido, nomeProduto, novosValores);

                    Solver.println("\nItem editado com sucesso!");
                    break;
                } catch (NumberFormatException e) {
                    Solver.println("\nInforme uma quantidade numérica, por favor.\n");
                } catch (Exception e) {
                    Solver.println(e.getMessage());
                }
            }
        }

    }

    public static void gerenciarCardapio(Cafeteria caf) throws Exception {
        Solver.println("Gerenciamento do cardápio:");
        Solver.println("  1. Adicionar bebida");
        Solver.println("  2. Adicionar lanche");
        Solver.println("  3. Remover produto");
        Solver.println("  4. Editar produto");
        Solver.println("  5. Exibir cardápio");
        Solver.print("Opção: ");
        String opcaoCardapio = Solver.input();
        System.out.println();
        switch (opcaoCardapio) {
            case "1":
                adicionarBebida(caf);
                break;
            case "2":
                adicionarLanche(caf);
                break;
            case "3":
                removerProduto(caf);
                break;
            case "4":
                editarProduto(caf);
                break;
            case "5":
                if (verificarCardapioVazio(caf))
                    return;
                caf.exibirMenu();
                break;
            default:
                Solver.println("Comando inválido. Tente novamente.");
                break;
        }
    }

    public static void adicionarBebida(Cafeteria caf) {
        String nomeBebida = null;

        do {
            try {
                Solver.println("Adicionar bebida ao cardápio");
                Solver.print("Nome: ");
                nomeBebida = Solver.input();

                if (!nomeBebida.matches("[a-zA-Z]+")) {
                    throw new Exception("\nO nome da bebida deve conter apenas letras.\n");
                }

                if (caf.getMenu().getProdutoIndex(nomeBebida) != -1) {
                    throw new Exception("\nJá existe uma bebida com esse nome no cardápio.\n");
                }

                break;
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                Solver.print("Preço (tamanho pequeno): ");
                Double precoP = Double.parseDouble(Solver.input());
                if (precoP <= 0) {
                    throw new Exception("\nInforme um preço maior que zero, por favor.\n");
                }

                Solver.print("Preço (tamanho médio): ");
                Double precoM = Double.parseDouble(Solver.input());
                if (precoM <= 0) {
                    throw new Exception("\nInforme um preço maior que zero, por favor.\n");
                }

                Solver.print("Preço (tamanho grande): ");
                Double precoG = Double.parseDouble(Solver.input());
                if (precoG <= 0) {
                    throw new Exception("\nInforme um preço maior que zero, por favor.\n");
                }

                caf.getMenu().adicionarBebida(new Bebida(nomeBebida), precoP, precoM, precoG);
                Solver.println("\nBebida adicionada com sucesso!");

                break;

            } catch (NumberFormatException e) {
                Solver.println("\nInforme preços válidos, por favor.\n");
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        } while (true);

    }

    public static void adicionarLanche(Cafeteria caf) {
        String nomeLanche = null;

        do {
            try {
                Solver.println("Adicionar lanche ao cardápio");
                Solver.print("Nome: ");
                nomeLanche = Solver.input();

                if (!nomeLanche.matches("[a-zA-Z]+")) {
                    throw new Exception("\nO nome do lanche deve conter apenas letras.\n");
                }

                if (caf.getMenu().getProdutoIndex(nomeLanche) != -1) {
                    throw new Exception("\nJá existe um lanche com esse nome no cardápio.\n");
                }

                break;
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                Solver.print("Preço: ");
                Double preco = Double.parseDouble(Solver.input());

                if (preco <= 0) {
                    throw new Exception("\nInforme um preço maior que zero, por favor.\n");
                }

                caf.getMenu().adicionarLanche(new Lanche(nomeLanche, preco));

                Solver.println("\nLanche adicionado com sucesso!");

                break;
            } catch (NumberFormatException e) {
                Solver.println("\nInforme preços válidos, por favor.\n");
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        } while (true);
    }

    public static void removerProduto(Cafeteria caf) throws Exception {
        if (verificarCardapioVazio(caf))
            return;

        while (true) {
            try {
                caf.exibirMenu();
                System.out.println();
                Solver.println("Remover produto do cardápio");
                Solver.print("Nome: ");
                String nomeProduto = Solver.input();

                if (caf.getMenu().getProdutoIndex(nomeProduto) == -1) {
                    throw new Exception("\nProduto não encontrado no cardápio.\n");
                }

                caf.getMenu().removerProduto(nomeProduto);

                Solver.println("\nProduto removido com sucesso!");
                break;
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }
        }
    }

    public static boolean verificarCardapioVazio(Cafeteria caf) {
        if (caf.getMenu().getBebidas().isEmpty() && caf.getMenu().getLanches().isEmpty()) {
            Solver.println("O cardápio está vazio.");
            return true;
        }
        return false;
    }

    public static boolean verificarPedidosVazio(Cafeteria caf) {
        if (caf.getPedidos().isEmpty()) {
            Solver.println("Não há pedidos.");
            return true;
        }
        return false;
    }

    public static void editarProduto(Cafeteria caf) throws Exception {
        if (verificarCardapioVazio(caf))
            return;

        while (true) {
            try {
                caf.exibirMenu();
                System.out.println();

                String nomeProduto;
                while (true) {
                    Solver.print("Digite o nome do produto que deseja editar (ou 's' para sair): ");
                    nomeProduto = Solver.input();

                    if (nomeProduto.equalsIgnoreCase("s")) {
                        return;
                    }

                    if (caf.getMenu().getProdutoIndex(nomeProduto) != -1) {
                        break;
                    }

                    Solver.println("\nProduto não encontrado no cardápio. Tente novamente.\n");
                }

                Map<String, Object> novosValores = new HashMap<>();

                String atributo;
                while (true) {
                    Solver.print("Digite o atributo que deseja editar (nome, preco, etc.): ");
                    atributo = Solver.input();

                    if (caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(nomeProduto)) instanceof Bebida) {
                        if (atributo.equals("nome") || atributo.equals("precoPequeno") ||
                                atributo.equals("precoMedio") || atributo.equals("precoGrande")) {
                            break;
                        }
                    }

                    if (caf.getMenu().getProduto(caf.getMenu().getProdutoIndex(nomeProduto)) instanceof Lanche) {
                        if (atributo.equals("nome") || atributo.equals("preco")) {
                            break;
                        }
                    }

                    Solver.println("\nAtributo não encontrado. Tente novamente.\n");
                }

                Object novoValor;
                while (true) {
                    Solver.print("Digite o novo valor: ");
                    try {
                        String input = Solver.input();

                        if (atributo.startsWith("preco")) {
                            novoValor = Double.parseDouble(input);
                            break;
                        } else if (input.matches(".*\\d+.*")) {
                            throw new NumberFormatException();
                        } else {
                            novoValor = input;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        Solver.println("\nValor inválido.\n");
                    }
                }

                novosValores.put(atributo, novoValor);

                caf.getMenu().editarProduto(nomeProduto, novosValores);

                Solver.println("\nProduto editado com sucesso!\n");
            } catch (Exception e) {
                Solver.println(e.getMessage());
            }

            Solver.print("Deseja editar mais algum produto? (s/n): ");
            if (!Solver.input().toLowerCase().equals("s")) {
                break;
            }
        }
    }
}
