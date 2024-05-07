import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArvAVL extends ArvBin {
	ArvAVL(int len) {
		super(len);
	}

	@Override
	public void insert(String value) {
		super.insert(value);
		// balanceia a árvore com base no critérios de balanceamento da árvore AVL
		if (!isBalanced()) { // verifica se a árvore está balanceada antes de balancear
			this.toBalance();
		}
	}

	@Override
	public boolean remove(String v) {
		super.remove(v);

		if (!isBalanced())
			this.toBalance();

		updateIndexLastNode();

		return true;
	}

	// rotação para a direita
	private void rightRotation(int i) {
		String leftNode = getNode(nodeLeft(i));
		String parent = getNode(i);

		List<String> subtreeNodes = new ArrayList<>();

		subtreeNodes = collectAndRemoveSubtree(i);

		super.insert(leftNode);
		super.insert(parent);

		for (String v : subtreeNodes)
			super.insert(v);

		updateIndexLastNode();
	}

	private void leftRotation(int i) {
		String rightNode = getNode(nodeRight(i));
		String parent = getNode(i);
		List<String> subtreeNodes = new ArrayList<>();

		subtreeNodes = collectAndRemoveSubtree(i);

		super.insert(rightNode);
		super.insert(parent);

		for (String v : subtreeNodes)
			super.insert(v);

		updateIndexLastNode();
	}

	private void toBalance() {
		for (int i = lastNode; i >= 0; i--)
			if (getNode(i) != null && !this.isNodeBalance(i)) {
				if (i > lastNode)
					return;

				int height = height(nodeLeft(i)) - height(nodeRight(i));

				if (height < 0) {
					int rightHeight = height(nodeLeft(nodeRight(i))) - height(nodeRight(nodeRight(i)));

					if (rightHeight <= 0) {
						this.leftRotation(i);
					} else {
						this.rightRotation(nodeRight(i));
						updateIndexLastNode();
						this.leftRotation(i);
					}
				} else {
					int leftHeight = height(nodeLeft(nodeLeft(i))) - height(nodeRight(nodeLeft(i)));

					if (leftHeight >= 0) {
						this.rightRotation(i);

					} else {

						this.leftRotation(nodeLeft(i));
						updateIndexLastNode();
						this.rightRotation(i);
					}
				}

				updateIndexLastNode();
			}

	}

	// checa se o nó está balanceado
	private boolean isNodeBalance(int i) {
		int height = height(nodeLeft(i)) - height(nodeRight(i));

		if (Math.abs(height) > 1)
			return false;
		else
			return true;
	}

	private void swapMaxLeft(int i) {
		int maxIndex = max(nodeLeft(i));
		String maxString = getNode(maxIndex);

		super.remove(maxString);
		setNode(i, maxString);
	}

	private int max(int i) {
		if (getNode((nodeRight(i))) == null)
			return i;

		return max(nodeRight(i));
	}

	private void swapMinRight(int i) {
		int minIndex = min(nodeRight(i));
		String minString = getNode(minIndex);

		super.remove(minString);
		setNode(i, minString);
	}

	private int min(int i) {
		if (getNode((nodeLeft(i))) == null)
			return i;

		return min(nodeLeft(i));
	}

	// coleta os valores dos nós da subárvore e remove os nós
	private List<String> collectAndRemoveSubtree(int i) {
		List<String> nodeValues = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		stack.push(i);

		while (!stack.isEmpty()) {
			int currentNodeIndex = stack.pop();
			String nodeValue = getNode(currentNodeIndex);

			if (nodeValue != null) {
				nodeValues.add(nodeValue);
				setNode(currentNodeIndex, null);

				// primeiro adiciona os nós da direita, para que o nó esquerdo seja processado
				// antes (em ordem)
				int right = nodeRight(currentNodeIndex);
				int left = nodeLeft(currentNodeIndex);

				if (right != -1)
					stack.push(right);

				if (left != -1)
					stack.push(left);

			}
		}

		return nodeValues;
	}
}