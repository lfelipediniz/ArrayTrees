import java.util.List;
import java.util.ArrayList;

public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        if (!isBalanced()) {
            toBalance();
        }
    }

    protected void toBalance() {
        List<String> elements = new ArrayList<>();

        for (String element : heap)
            if (element != null)
                elements.add(element);

        elements.sort(String::compareTo);

        for (int i = 0; i < capacity; i++)
            heap[i] = null;

        size = 0;
        insertBalanced(0, elements.size() - 1, elements);
    }

    protected void insertBalanced(int a, int b, List<String> elements) {
        if (a > b)
            return;

        int mid = (a + b) / 2;
        super.insert(elements.get(mid));
        insertBalanced(a, mid - 1, elements);
        insertBalanced(mid + 1, b, elements);
    }

    @Override
    public boolean remove(String value) {
        boolean result = super.remove(value);
        
        if (!isBalanced())
            toBalance();

        return result;
    }
}
