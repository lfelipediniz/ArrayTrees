import java.util.Arrays;

public class BalancedArrayBST {
    private Integer[] tree;
    private int size;

    public BalancedArrayBST(int capacity) {
        tree = new Integer[capacity];
        size = 0;
    }

    // Método para criar a ABB perfeitamente balanceada
    public void createPerfectlyBalancedBST(Integer[] elements) {
        Arrays.sort(elements); // Passo 1
        size = elements.length;
        buildBST(elements, 0, elements.length - 1, 0); // Passo 2, 3 e 4
    }

    // Método auxiliar para construir a árvore recursivamente
    private void buildBST(Integer[] elements, int start, int end, int index) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        tree[index] = elements[mid]; // Passo 2

        // Construir sub-árvore esquerda
        buildBST(elements, start, mid - 1, 2 * index + 1); // Passo 3

        // Construir sub-árvore direita
        buildBST(elements, mid + 1, end, 2 * index + 2); // Passo 4
    }

    // Método para rebalancear a árvore
    public void rebalance() {
        Integer[] elements = new Integer[size];
        inorderTraversal(tree, 0, elements, new int[]{0}); // Passo 1
        buildBST(elements, 0, size - 1, 0); // Passo 2 a 5
    }

    // Método auxiliar para coletar elementos em ordem
    private void inorderTraversal(Integer[] tree, int index, Integer[] elements, int[] elementIndex) {
        if (index >= tree.length || tree[index] == null) {
            return;
        }

        // Visitar sub-árvore esquerda
        inorderTraversal(tree, 2 * index + 1, elements, elementIndex);

        // Visitar nó atual
        elements[elementIndex[0]++] = tree[index];

        // Visitar sub-árvore direita
        inorderTraversal(tree, 2 * index + 2, elements, elementIndex);
    }

    // Método para exibir a árvore em ordem
    public void printInOrder() {
        printInOrder(0);
        System.out.println();
    }

    private void printInOrder(int index) {
        if (index >= tree.length || tree[index] == null) {
            return;
        }

        printInOrder(2 * index + 1); // Esquerda
        System.out.print(tree[index] + " "); // Nó
        printInOrder(2 * index + 2); // Direita
    }

    public static void main(String[] args) {
        BalancedArrayBST bst = new BalancedArrayBST(15);
        Integer[] elements = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };
        bst.createPerfectlyBalancedBST(elements);
        bst.printInOrder();

        // Para testar o rebalanceamento
        // bst.rebalance();
        // bst.printInOrder();
    }
}
