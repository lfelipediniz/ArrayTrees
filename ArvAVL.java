public class ArvAVL extends ArvBin {
    public ArvAVL(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        // Implementar rotinas de balanceamento AVL aqui
    }

    @Override
    public boolean remove(String value) {
        boolean result = super.remove(value);
        // Implementar rotinas de balanceamento AVL aqui
        return result;
    }
}
