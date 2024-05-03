import java.util.ArrayList;
import java.util.List;

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
            indexlastNode(); // Atualiza o lastNode se necessário
        } else {
            System.out.println("Árvore cheia");
        }
    }

    public boolean find(String v) {
        return findIndex(v) != -1;
    }

    public boolean remove(String v) {
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

        indexlastNode(); // Atualiza o índice do último nó após a remoção
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

    public int len() {
        return size;
    }

    // retorna uma string que representa a árvore em formato de grafo
    public String toString() {
        if (size == 1 && heap[0] != null) {
            // Caso especial: a árvore possui apenas o nó raiz e ele não é nulo
            return String.format("digraph {\n\"0 %s\" }\n", heap[0]);
        }
        StringBuilder sb = new StringBuilder("digraph {\n");
        for (int i = 0; i < size; i++) {
            if (getNode(i) == null) continue;

            int left = nodeLeft(i);
            int right = nodeRight(i);
            if (left < size && getNode(left) != null) {
                sb.append(String.format("\"%d %s\" -> \"%d %s\"\n", i, getNode(i), left, getNode(left)));
            }
            if (right < size && getNode(right) != null) {
                sb.append(String.format("\"%d %s\" -> \"%d %s\"\n", i, getNode(i), right, getNode(right)));
            }
        }
        sb.append("}");
        return sb.toString();
    }
    // métodos auxiliares

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

    protected void indexlastNode() {
        // Atualiza o índice do último nó baseado na ocupação atual do array
        for (int i = heap.length - 1; i >= 0; i--) {
            if (heap[i] != null) {
                lastNode = i;
                return;
            }
        }
        lastNode = -1;
    }

}
