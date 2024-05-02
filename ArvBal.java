public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        int p = 0;

        abb_insert_string(value, p);

    }

    private void abb_insert_string(String value, int p) {
        if (p < capacity) {
            if (heap[p] == null) {
                heap[p] = value;
                size = Math.max(size, p + 1);
            } else {
                if (value.compareTo(heap[p]) < 0) {
                    abb_insert_string(value, 2 * p + 1);
                } else if (value.compareTo(heap[p]) > 0) {
                    abb_insert_string(value, 2 * p + 2);
                }
            }
        } else {
            System.out.println("Árvore cheia");
        }
    }

    // @Override
    // public boolean remove(String value) {


    // }
}
