import java.util.Scanner;

public class Solver {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] arg) {
        Cafeteria caf = new Cafeteria();

        while (true) {
            MenuOp.exibirMenuPrincipal();
            String option = input();
            print("\n");

            try {
                switch (option) {
                    case "1":
                        MenuOp.gerenciarPedidos(caf);
                        print("\n");
                        break;
                    case "2":
                        MenuOp.gerenciarCardapio(caf);
                        print("\n");
                        break;
                    case "3":
                        caf.visualizarTransacoes();
                        print("\n");
                        break;
                    default:
                        println("Comando inválido.");
                        break;
                }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
    }

    public static void println(Object value) {
        System.out.println(value);
    }

    public static void print(Object value) {
        System.out.print(value);
    }

    public static String input() {
        return scanner.nextLine();
    }

    public static double number(String value) {
        return Double.parseDouble(value);
    }
}
