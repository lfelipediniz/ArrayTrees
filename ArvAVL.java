import java.util.ArrayList;
import java.util.List;

public class ArvAVL extends ArvBin {
    ArvAVL(int len) {
        super(len);
    }

    @Override
    // insere um valor value na AVL
    public void insert(String value) {
        super.insert(value);
        // balanceia a árvore
        this.balance();

    }

    @Override
    public boolean remove(String v) {

        if (!find(v)) {
            return false; // se o valor não for encontrado, retorna falso
        }

        int i = findIndex(v); // encontra o índice do valor a ser removido

        // se o nó não tiver filhos, ele é removido
        if (getNode(nodeLeft(i)) == null && getNode(nodeRight(i)) == null) {
            super.remove(v);
            return true;
        }

        // caso o nó tenha apenas um filho, ele é removido e o filho é inserido no lugar
        if (getNode(nodeLeft(i)) != null) {
            swapMaxLeft(i);
        } else {
            swapMinRight(i);
        }

        this.balance();

        indexlastNode();

        return true;
    }

    // rotação para a direita
    private void rightRotation(int i) {

        String leftNode = getNode(nodeLeft(i));
        String parent = getNode(i);
        List<String> list = new ArrayList<>();

        list = search(i);

        super.insert(leftNode);
        super.insert(parent);

        for (String v : list) {
            super.insert(v);
        }

        indexlastNode();
    }

    private void leftRotation(int i) {

        String rightNode = getNode(nodeRight(i));
        String parent = getNode(i);
        List<String> list = new ArrayList<>();

        list = search(i);

        super.insert(rightNode);

        super.insert(parent);

        for (String v : list) {
            super.insert(v);
        }

        indexlastNode();
    }

    private void balance() {

        for (int i = this.lastNode; i >= 0; i--) {

            if (getNode(i) != null && !this.nodeBalance(i)) {
                this.balanceTree(i);
            }

        }
    }

    // checa se o nó está balanceado
    private boolean nodeBalance(int i) {

        int height = nodeHeight(nodeLeft(i)) - nodeHeight(nodeRight(i));

        if (Math.abs(height) > 1) {
            return false;
        } else {
            return true;
        }

    }

    private int nodeHeight(int i) {

        if (i > this.lastNode || getNode(i) == null)
            return 0;

        int leftHeight = this.nodeHeight(nodeLeft(i));
        int rightHeight = this.nodeHeight(nodeRight(i));

        return Math.max(leftHeight, rightHeight) + 1;

    }

    private void balanceTree(int i) {

        if (i > this.lastNode) {
            return;
        }

        int height = nodeHeight(nodeLeft(i)) - nodeHeight(nodeRight(i));

        if (height < 0) {

            int rightHeight = nodeHeight(nodeLeft(nodeRight(i))) - nodeHeight(nodeRight(nodeRight(i)));

            if (rightHeight <= 0) {
                this.leftRotation(i);

            } else {

                this.rightRotation(nodeRight(i));

                this.indexlastNode();
                this.leftRotation(i);

            }

        } else {

            int leftHeight = nodeHeight(nodeLeft(nodeLeft(i))) - nodeHeight(nodeRight(nodeLeft(i)));

            if (leftHeight >= 0) {
                this.rightRotation(i);

            } else {

                this.leftRotation(nodeLeft(i));

                this.indexlastNode();
                this.rightRotation(i);

            }

        }

        this.indexlastNode();

    }

    private void swapMaxLeft(int i) {

        int maxIndex = max(nodeLeft(i));
        String maxString = getNode(maxIndex);

        super.remove(maxString);
        setNode(i, maxString);

    }

    private int max(int i) {

        if (getNode((nodeRight(i))) == null) {
            return i;
        }

        return max(nodeRight(i));
    }

    private void swapMinRight(int i) {

        int minIndex = min(nodeRight(i));
        String minString = getNode(minIndex);

        super.remove(minString);

        setNode(i, minString);
    }

    private int min(int i) {

        if (getNode((nodeLeft(i))) == null) {
            return i;
        }

        return min(nodeLeft(i));
    }

    private List<String> search(int i) {

        List<String> strList = new ArrayList<>();

        strList.add(getNode(i));
        setNode(i, null);

        searchAux(strList, nodeLeft(i));
        searchAux(strList, nodeRight(i));

        return strList;
    }

    private void searchAux(List<String> strList, int cur) {

        if (getNode(cur) == null)
            return;

        strList.add(getNode(cur));
        setNode(cur, null);

        searchAux(strList, nodeLeft(cur));
        searchAux(strList, nodeRight(cur));
    }

}