import java.util.List;
import java.util.ArrayList;

public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        if (!isBalanced()) {
            toBalance();
        }
    }

    protected boolean isBalanced() {
        return isBalanced(0); // inicia a verificação a partir da raiz
    }

    private boolean isBalanced(int i) {
        if (i >= size || getNode(i) == null) {
            return true; // Um nó nulo é considerado balanceado
        }

        int leftHeight = height(nodeLeft(i));
        int rightHeight = height(nodeRight(i));

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false; // Diferença de altura maior que 1
        }

        // Verifica recursivamente para subárvores esquerda e direita
        return isBalanced(nodeLeft(i)) && isBalanced(nodeRight(i));
    }

    private int height(int i) {
        if (i >= size || getNode(i) == null) {
            return 0; // A altura de um nó nulo é 0
        }
        // Calcula a altura de forma recursiva
        return 1 + Math.max(height(nodeLeft(i)), height(nodeRight(i)));
    }

    protected void toBalance() {
        List<String> elements = new ArrayList<>();
        for (String element : heap) {
            if (element != null) {
                elements.add(element);
            }
        }

        elements.sort(String::compareTo);

        for (int i = 0; i < capacity; i++) {
            heap[i] = null;
        }

        size = 0;

        insertBalanced(0, elements.size() - 1, elements);
    }

    protected void insertBalanced(int a, int b, List<String> elements) {
        if (a > b) {
            return;
        }
        int mid = (a + b) / 2;
        super.insert(elements.get(mid));
        insertBalanced(a, mid - 1, elements);
        insertBalanced(mid + 1, b, elements);
    }

    @Override
    public boolean remove(String value) {
        boolean result = super.remove(value);
        if (!isBalanced()) {
            toBalance();
        }
        return result;
    }
}
