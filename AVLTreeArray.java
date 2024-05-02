import java.util.Arrays;

public class AVLTreeArray {
    private Integer[] tree;
    private int rootIndex;
    private int size;

    public AVLTreeArray(int capacity) {
        tree = new Integer[capacity];
        rootIndex = -1;  // Inicialmente não há raiz
        size = 0;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int height(int index) {
        if (index >= tree.length || tree[index] == null) return 0;
        return 1 + Math.max(height(left(index)), height(right(index)));
    }

    private int getBalance(int index) {
        if (index >= tree.length || tree[index] == null) return 0;
        return height(left(index)) - height(right(index));
    }

    public void insert(int value) {
        rootIndex = insert(rootIndex, value);
    }

    private int insert(int index, int value) {
        if (index >= tree.length || tree[index] == null) {
            if (index >= tree.length) {
                // Expandir o array se necessário
                tree = Arrays.copyOf(tree, tree.length * 2);
            }
            tree[index] = value;
            size++;
            return index;
        }

        if (value < tree[index]) {
            int leftIndex = insert(left(index), value);
            tree[left(index)] = tree[leftIndex];
        } else {
            int rightIndex = insert(right(index), value);
            tree[right(index)] = tree[rightIndex];
        }

        // Balanço após inserção
        return balance(index);
    }

    private int balance(int index) {
        int balance = getBalance(index);

        if (balance > 1) {  // Caso esquerdo
            if (getBalance(left(index)) < 0) {
                tree[left(index)] = rotateLeft(left(index));
            }
            index = rotateRight(index);
        } else if (balance < -1) {  // Caso direito
            if (getBalance(right(index)) > 0) {
                tree[right(index)] = rotateRight(right(index));
            }
            index = rotateLeft(index);
        }

        return index;
    }

    private int rotateLeft(int index) {
        int rightIndex = right(index);
        tree[index] = tree[right(rightIndex)];
        tree[right(index)] = tree[left(rightIndex)];
        tree[left(rightIndex)] = tree[index];
        return rightIndex;
    }

    private int rotateRight(int index) {
        int leftIndex = left(index);
        tree[index] = tree[left(leftIndex)];
        tree[left(index)] = tree[right(leftIndex)];
        tree[right(leftIndex)] = tree[index];
        return leftIndex;
    }

    public void printTree() {
        for (int i = 0; i < size; i++) {
            if (tree[i] != null) {
                System.out.print(tree[i] + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AVLTreeArray avl = new AVLTreeArray(10);
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        avl.printTree();
    }
}
