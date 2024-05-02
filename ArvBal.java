import java.util.List;
import java.util.ArrayList;

public class ArvBal extends ArvBin {
    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String value) {
        super.insert(value);
        toBalance();

    }

    protected void toBalance() {
        // salvar todos os elementos da árvore em uma lista auxiliar
        List<String> elements = new ArrayList<>();
        for (String element : heap) {
            if (element != null) {
                elements.add(element);
            }
        }

        // ordenar elementos da lista de forma crescente
        elements.sort(String::compareTo);

        // percorre o heap e atribui null
        for (int i = 0; i < capacity; i++) {
            heap[i] = null;
        }

        size = 0;

        insertBalanced(0, elements.size() - 1, elements);
    }

    protected void insertBalanced(int a, int b, List<String> elements) {
        if (a > b)
            return;
        int meio = (a + b) / 2;
        super.insert(elements.get(meio));
        insertBalanced(a, meio - 1, elements);
        insertBalanced(meio + 1, b, elements);
    }

    @Override
    public boolean remove(String value) {
        boolean result = super.remove(value);
        toBalance();

        return result;
    }
}
