import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvBin arvore = new ArvBin(1000); // Supõe que a árvore pode ter até 1000 elementos
        // ArvBal arvoreBalanceada = new ArvBal(1000);

        ArvBal arvoreBalanceada = new ArvBal(1000);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] command = input.split(" ");
            if (command.length != 2) {
                continue; // ignora linhas mal formatadas
            }

            String operation = command[0];
            String value = command[1];

            switch (operation) {
                case "i":
                    arvore.insert(value);
                    arvoreBalanceada.insert(value);
                    break;
                case "d":
                    if (arvore.remove(value) == false) {
                        System.out.println("Não encontrado para remover: " + value);
                    }

                    if (arvoreBalanceada.remove(value) == false) {
                        System.out.println("Não encontrado para remover: " + value);
                    }
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
                    break;
            }
        }
        System.out.println(arvore.toString());
        System.out.println(arvoreBalanceada.toString());
        scanner.close();
    }
}
