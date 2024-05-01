import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvBin arvore = new ArvBin(100); // Supõe que a árvore pode ter até 100 elementos

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] command = input.split(" ");
            if (command.length != 2) {
                continue; // Ignora linhas mal formatadas
            }

            String operation = command[0];
            String value = command[1];

            switch (operation) {
                case "i":
                    arvore.insert(value);
                    System.out.println("Inserido: " + value);
                    break;
                case "d":
                    if (arvore.remove(value)) {
                        System.out.println("Removido: " + value);
                    } else {
                        System.out.println("Não encontrado para remover: " + value);
                    }
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
                    break;
            }
        }

        System.out.println("Estado final da árvore:");
        System.out.println(arvore.toString());
        scanner.close();
    }
}
