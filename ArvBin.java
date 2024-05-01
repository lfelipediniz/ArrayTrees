public class ArvBin {
    private String[] heap;
    private int size;

    // Construtor
    public ArvBin(int len) {
        heap = new String[len];
        size = 0;
    }

    // Insere uma string na árvore
    public void insert(String value) {
        if (size == heap.length) {
            System.out.println("Heap está cheio");
            return;
        }
        heap[size] = value;
        size++;
    }

    // Verifica se o elemento está presente
    public boolean find(String v) {
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    // Remove um elemento da árvore
    public boolean remove(String v) {
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(v)) {
                heap[i] = heap[size - 1]; // move o último elemento para o local do elemento removido
                heap[size - 1] = null; // Remove a referência do último elemento
                size--;
                return true;
            }
        }
        return false;
    }

    // retorna o número de elementos presentes na árvore
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
