public class ArvBin {
    private String[] heap;
    private int size;

    // construtor
    public ArvBin(int len) {
        heap = new String[len];
        size = 0;
    }

    // insere uma string na árvore
    public void insert(String value) {
        if (size == heap.length) {
            System.out.println("Heap está cheio");
            return;
        }
        heap[size] = value;
        size++;
    }

    // verifica se o elemento está presente
    public boolean find(String v) {
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    // remove um elemento da árvore
    public boolean remove(String v) {
        for (int i = 0; i < size; i++) {
            if (heap[i] != null && heap[i].equals(v)) {
                heap[i] = heap[size - 1]; // Move o último elemento para o local do elemento removido
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
}
