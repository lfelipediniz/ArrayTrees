public class ArvBin {
    protected String[] tree;
    private int size;

    public ArvBin(int len) {
        tree = new String[len];
        size = 0;
    }

    public void insert(String value) {
        if (size == tree.length) {
            throw new RuntimeException("Tree is full");
        }
        tree[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && tree[parent(index)].compareTo(tree[index]) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public boolean remove(String value) {
        for (int i = 0; i < size; i++) {
            if (tree[i].equals(value)) {
                tree[i] = tree[size - 1];
                tree[size - 1] = null;
                size--;
                heapifyDown(i);
                return true;
            }
        }
        return false;
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        if (leftIndex < size && tree[leftIndex].compareTo(tree[smallest]) < 0) {
            smallest = leftIndex;
        }

        if (rightIndex < size && tree[rightIndex].compareTo(tree[smallest]) < 0) {
            smallest = rightIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    public boolean find(String value) {
        for (String s : tree) {
            if (s != null && s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int len() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("digraph {\n");
        for (int i = 0; i < size; i++) {
            if (leftChild(i) < size) {
                sb.append("\"").append(tree[i]).append("\" -> \"").append(tree[leftChild(i)]).append("\"\n");
            }
            if (rightChild(i) < size) {
                sb.append("\"").append(tree[i]).append("\" -> \"").append(tree[rightChild(i)]).append("\"\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    protected int leftChild(int i) {
        return 2 * i + 1;
    }

    protected int rightChild(int i) {
        return 2 * i + 2;
    }

    protected int parent(int i) {
        return (i - 1) / 2;
    }

    protected void swap(int i, int j) {
        String temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }
}
