public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        // Implementar rotinas de balanceamento específicas aqui
    }

    @Override
    public boolean remove(String value) {
        boolean result = super.remove(value);
        // Implementar rotinas de balanceamento específicas aqui
        return result;
    }
}
