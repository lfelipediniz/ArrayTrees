import java.util.List;
import java.util.ArrayList;

public class ArvBin {
    protected String[] heap;
    protected int size;
    protected int capacity;
    protected int lastNode = -1;

    public ArvBin(int len) {
        heap = new String[len];
        size = 0;
        capacity = len;
    }

    public void insert(String value) {
        int i = 0;
        while (i < size && getNode(i) != null) {
            if (value.compareTo(getNode(i)) < 0) {
                i = nodeLeft(i);
            } else if (value.compareTo(getNode(i)) > 0) {
                i = nodeRight(i);
            } else {
                return; // elemento já existe
            }
        }

        if (i < capacity) {
            setNode(i, value);
            size = Math.max(size, i + 1);
            updateIndexLastNode(); // Atualiza o lastNode se necessário
        } else {
            System.out.println("Árvore cheia");
        }
    }

    public boolean find(String v) {
        return findIndex(v) != -1;
    }

    public boolean remove(String v) {
        if (!find(v)) {
            return false;
        }

        // encontra o index do nó a ser removido
        int i = findIndex(v);

        // se o nó não tiver filhos, ele é removido
        if (getNode(nodeLeft(i)) == null && getNode(nodeRight(i)) == null) {
            isRemoveNode(v);
            return true;
        }

        // caso o nó tenha apenas um filho, ele é removido e o filho é inserido no lugar
        if (getNode(nodeLeft(i)) != null)
            swapMaxLeft(i);
        else
            swapMinRight(i);

        return true;
    }

    private boolean isRemoveNode(String v) {
        int index = findIndex(v);
        if (index == -1)
            return false; // elemento não encontrado

        List<String> elements = new ArrayList<>();
        collectSubtreeElements(index, elements); // coleta elementos da subárvore
        clearSubtree(index); // limpa a subárvore

        for (String element : elements) {
            if (!element.equals(v)) { // reinsere todos os elementos exceto o removido
                insert(element);
            }
        }

        updateIndexLastNode(); // atualiza o índice do último nó após a remoção
        return true;
    }

    protected int findIndex(String v) {
        int i = 0;
        while (i < size && getNode(i) != null) {
            if (v.compareTo(getNode(i)) < 0) {
                i = nodeLeft(i);
            } else if (v.compareTo(getNode(i)) > 0) {
                i = nodeRight(i);
            } else {
                return i;
            }
        }
        return -1; // não encontrado
    }

    public int len() {
        return size;
    }

    // retorna uma string que representa a árvore em formato de grafo
    public String toString() {
        if (size == 1 && heap[0] != null) {
            // caso especial: a árvore possui apenas o nó raiz e ele não é nulo
            return String.format("digraph {\n\"0 %s\" }\n", heap[0]);
        }
        StringBuilder sb = new StringBuilder("digraph {\n");
        for (int i = 0; i < size; i++) {
            if (getNode(i) == null)
                continue;

            int left = nodeLeft(i);
            int right = nodeRight(i);
            if (left < size && getNode(left) != null) {
                sb.append(String.format("\"%d %s\" ->\"%d %s\"\n", i, getNode(i), left, getNode(left))); // Espaço
                                                                                                         // removido
                                                                                                         // aqui
            }
            if (right < size && getNode(right) != null) {
                sb.append(String.format("\"%d %s\" ->\"%d %s\"\n", i, getNode(i), right, getNode(right))); // Espaço
                                                                                                           // removido
                                                                                                           // aqui
            }
        }
        sb.append("}\n"); // Adiciona uma quebra de linha extra aqui
        return sb.toString();
    }

    private void collectSubtreeElements(int index, List<String> elements) {
        if (index >= size || getNode(index) == null)
            return;
        elements.add(getNode(index));
        collectSubtreeElements(nodeLeft(index), elements); // esquerda
        collectSubtreeElements(nodeRight(index), elements); // direita
    }

    private void clearSubtree(int index) {
        if (index >= size || getNode(index) == null)
            return;
        setNode(index, null);
        clearSubtree(nodeLeft(index)); // esquerda
        clearSubtree(nodeRight(index)); // direita
    }

    private void swapMaxLeft(int i) {
        int maxIndex = max(nodeLeft(i));
        String maxString = getNode(maxIndex);

        isRemoveNode(maxString);
        setNode(i, maxString);
    }

    private int max(int i) {
        if (getNode((nodeRight(i))) == null)
            return i;

        return max(nodeRight(i));
    }

    private void swapMinRight(int i) {
        int minIndex = min(nodeRight(i));
        String minString = getNode(minIndex);

        isRemoveNode(minString);
        setNode(i, minString);
    }

    private int min(int i) {
        if (getNode((nodeLeft(i))) == null)
            return i;

        return min(nodeLeft(i));
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

        // verifica recursivamente para subárvores esquerda e direita
        return isBalanced(nodeLeft(i)) && isBalanced(nodeRight(i));
    }


    // métodos auxiliares para usar nas demais árvores também
    protected int height(int i) {
        if (i >= size || getNode(i) == null) {
            return 0; // A altura de um nó nulo é 0
        }
        // calcula a altura de forma recursiva
        return 1 + Math.max(height(nodeLeft(i)), height(nodeRight(i)));
    }

    protected int nodeLeft(int i) {
        return 2 * i + 1;
    }

    protected int nodeRight(int i) {
        return 2 * i + 2;
    }

    protected String getNode(int i) {
        if (i >= heap.length || i < 0)
            return null;
        return heap[i];
    }

    protected void setNode(int i, String value) {
        if (i < heap.length && i >= 0) {
            heap[i] = value;
        }
    }

    protected void updateIndexLastNode() {
        // atualiza o índice do último nó baseado na ocupação atual do array
        for (int i = heap.length - 1; i >= 0; i--) {
            if (heap[i] != null) {
                lastNode = i;
                return;
            }
        }
        lastNode = -1;
    }

    // conta os subnós
    protected int countNodes(int i) {
        if (i >= size || getNode(i) == null) {
            return 0; // se o nó não existe, retorna 0
        }

        // conta recursivamente os nós à esquerda e à direita, mais o próprio nó
        int leftCount = countNodes(nodeLeft(i));
        int rightCount = countNodes(nodeRight(i));
        return 1 + leftCount + rightCount;
    }

    // verifica se a árvore está balanceada
    protected boolean isBalanced() {
        return isBalanced(0); // inicia a verificação a partir da raiz
    }
}
