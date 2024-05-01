import java.util.ArrayList;
import java.util.List;

public class ArvBin {
    private String[] heap;
    private int size;
    private int capacity;

    public ArvBin(int len) {
        heap = new String[len];
        size = 0;
        capacity = len;
    }

    public void insert(String value) {
        int i = 0;
        while(i < size && heap[i] != null) {
            if(value.compareTo(heap[i]) < 0) {
                i = 2 * i + 1;
            } else if(value.compareTo(heap[i]) > 0){
                i = 2 * i + 2;
            } else {
                return; // elemento já existe
            }
        }

        if(i < capacity) {
            heap[i] = value;
            size = Math.max(size, i + 1);
        } else {
            System.out.println("Árvore cheia");
        }
    }

    public boolean find(String v) {
        int i = findIndex(v);
        return i != -1;
    }

    public boolean remove(String v) {
        int index = findIndex(v);
        if (index == -1) return false; // elemento não encontrado

        List<String> elements = new ArrayList<>();
        collectSubtreeElements(index, elements); // coleta elementos da subárvore
        clearSubtree(index); // limpa a subárvore

        for (String element : elements) {
            if (!element.equals(v)) { // reinsere todos os elementos exceto o removido
                insert(element);
            }
        }
        return true;
    }

    private int findIndex(String v) {
        int i = 0;
        while(i < size && heap[i] != null) {
            if(v.compareTo(heap[i]) < 0) {
                i = 2 * i + 1;
            } else if(v.compareTo(heap[i]) > 0){
                i = 2 * i + 2;
            } else {
                return i;
            }
        }
        return -1; // não encontrado
    }

    private void collectSubtreeElements(int index, List<String> elements) {
        if (index >= size || heap[index] == null) return;
        elements.add(heap[index]);
        collectSubtreeElements(2 * index + 1, elements); // esquerda
        collectSubtreeElements(2 * index + 2, elements); // direita
    }

    private void clearSubtree(int index) {
        if (index >= size || heap[index] == null) return;
        heap[index] = null;
        clearSubtree(2 * index + 1); // esquerda
        clearSubtree(2 * index + 2); // direita
    }

    public int len() {
        return size;
    }

    // retorna uma string que representa a árvore em formato de grafo
    public String toString() {
        StringBuilder sb = new StringBuilder("digraph {\n");
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < size && heap[left] != null) {
                sb.append(String.format("\"%d %s\" -> \"%d %s\"\n", i, heap[i], left, heap[left]));
            }
            if (right < size && heap[right] != null) {
                sb.append(String.format("\"%d %s\" -> \"%d %s\"\n", i, heap[i], right, heap[right]));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
