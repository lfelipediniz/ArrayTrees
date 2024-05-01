public class ArvBin {
    private String[] heap;
    private int size;
    private int capacity;

    // Construtor
    public ArvBin(int len) {
        heap = new String[len];
        size = 0;
        capacity = len;
    }

    // Insere uma string na árvore mantendo a ordem
    public void insert(String value) {
        int i = 0;
        while(i < size && heap[i] != null) {
            if(value.compareTo(heap[i]) < 0) {
                i = 2 * i + 1; // vai pro filho da esquerda
            } else if(value.compareTo(heap[i]) > 0){
                i = 2 * i + 2; // vai pro filho da direita
            } else {
                return; // elemento já existe
            }
        }

        if(i < capacity) {
            heap[i] = value;
            // size = i + 1; // atualiza o tamanho com base nos saltos que podemos dar quando escrevemosno vetor
        } else {
            System.out.println("Árvore cheia");
        }
    }

    // Verifica se o elemento está presente
    public boolean find(String v) {
        return true;
    }

    // Remove um elemento da árvore
    public boolean remove(String v) {
        return true;

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
