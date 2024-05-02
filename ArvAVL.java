import java.util.ArrayList;
import java.util.List;

public class ArvAVL extends ArvBin{

    //construtor da AVL
    ArvAVL(int len){
        super(len);    
    }

    @Override
    //insere um valor value na AVL
    public void insert(String value){

        super.insert(value);
        this.balance();

    }

    @Override
    //remove um valor v da AVL
    public boolean remove(String v){

        if(!find(v)) return false;

        int i = findIndex(v);

        //se o nó a ser removido não ter filhos, basta simplesmente removê-lo
        if(getNode(nodeLeft(i)) == null && getNode(nodeRight(i)) == null){

            super.remove(v);

            return true;

        }

        //se o nó tiver filhos, serão feitas mudanças de valores
        if(getNode(nodeLeft(i)) != null){
            swapMaxLeft(i);

        } else {
            swapMinRight(i);
        }

        //checa se a árvore está balanceada
        this.balance();
        //atualiza o valor do último nó da árvore
        indexlastNode();

        return true;
    }

    //rotação para a direita
    private void rightRotation(int i){

        String leftNode = getNode(nodeLeft(i));
        String parent = getNode(i);
        List<String> list = new ArrayList<>();
        
        //realiza uma busca a partir do nó atual
        list = search(i);
        
        //insere o nó à esquerda como um novo nó
        super.insert(leftNode);
        //insere o nó pai como um novo nó
        super.insert(parent);
        
        //insere todos os nós encontrados durante a busca
        for(String v : list){
            super.insert(v);
        }
        
        //atualiza o endereço do último nó
        indexlastNode();
    }
    
    //rotação para a esquerda
    private void leftRotation(int i){

        String rightNode = getNode(nodeRight(i));
        String parent = getNode(i);
        List<String> list = new ArrayList<>();
        
        //realiza uma busca a partir do nó atual
        list = search(i);
        
        //insere o nó à direita como um novo nó
        super.insert(rightNode);
        //insere o nó pai como um novo nó
        super.insert(parent);
        
        //insere todos os nós encontrados durante a busca
        for(String v : list){
            super.insert(v);
        }
        
        //atualiza o endereço do último nó
        indexlastNode();
    }
    

    //verifica se a AVL está balanceada
    private void balance(){

        //para cada nó, é feito uma checagem se ele está balanceado ou não
        for(int i = this.lastNode; i >= 0; i--){

            if(getNode(i)!= null && !this.nodeBalance(i)){
                this.balanceTree(i);
            }

        }
    }

    //verifica se o nó está balanceado através da diferença de alturas
    private boolean nodeBalance(int i){

        int height = nodeHeight(nodeLeft(i))- nodeHeight(nodeRight(i));

        if(Math.abs(height) > 1){
            return false;
        } else {
            return true;
        }

    }

    //função auxiliar para obter a altura dos nós
    private int nodeHeight(int i){

        if(i > this.lastNode || getNode(i)== null) return 0;

        int leftHeight = this.nodeHeight(nodeLeft(i));
        int rightHeight = this.nodeHeight(nodeRight(i));

        return Math.max(leftHeight, rightHeight) + 1; 

    }

    //se necessário, balanceia a AVL
    private void balanceTree(int i){

        if(i > this.lastNode){
            return;
        }

        //é obtido a altura de um nó i
        int height = nodeHeight(nodeLeft(i))- nodeHeight(nodeRight(i));

        //se essa altura for menor que zero, terá uma rotação para a esquerda
        if(height < 0){

            int rightHeight = nodeHeight(nodeLeft(nodeRight(i)))- nodeHeight(nodeRight(nodeRight(i)));

            //se a altura direita for menor ou igual a 0, é feita uma rotação simples para a esquerda
            if(rightHeight <= 0){
                this.leftRotation(i);

            //se não, é feita uma rotação direita-esquerda
            } else {

                this.rightRotation(nodeRight(i));
                //atualiza o endereço do último nó
                this.indexlastNode();
                this.leftRotation(i);
                
            }
        
        //se não, para a direita
        } else {

            int leftHeight = nodeHeight(nodeLeft(nodeLeft(i)))- nodeHeight(nodeRight(nodeLeft(i)));

            //se a altura esquerda for menor ou igual a 0, é feita uma rotação simples para a direita
            if(leftHeight >= 0){
                this.rightRotation(i);
            
            //se não, é feita uma rotação esquerda-direita
            } else {

                this.leftRotation(nodeLeft(i));
                //atualiza o endereço do último nó
                this.indexlastNode();
                this.rightRotation(i);

            }
            
        }

        //atualiza o endereço do último nó
        this.indexlastNode();

    }

    //troca o valor checado com o maior filho da esquerda
    private void swapMaxLeft(int i){

        int maxIndex = max(nodeLeft(i));
        String maxString = getNode(maxIndex);

        super.remove(maxString);
        setNode(i, maxString);        

    }

    //encontra o maior filho da esquerda
    private int max(int i){

        if(getNode((nodeRight(i)))== null){
            return i;
        }

        return max(nodeRight(i));
    }

    //troca o valor checado com o menor filho da esquerda
    private void swapMinRight(int i){

        int minIndex = min(nodeRight(i));
        String minString = getNode(minIndex);

        super.remove(minString);

        setNode(i, minString);
    }

    //encontra o menor filho da direita
    private int min(int i){

        if(getNode((nodeLeft(i))) == null){
            return i;
        }

        return min(nodeLeft(i));
    }
    
    //é utilizada uma lista para armazenar os valores da árvore corretamente
    private List<String> search(int i){

        List<String> strList = new ArrayList<>();

        //recursivamente, é adicionado todos os valores dessa subárvore na lista, e seus valores são transformados em null
        strList.add(getNode(i));
        setNode(i, null);

        searchAux(strList, nodeLeft(i));
        searchAux(strList, nodeRight(i));

        return strList;
    }

    //função auxiliar que percorre a arvore e armazena seus valores na lista
    private void searchAux(List<String> strList, int cur){

        //se a busca chegar ao fim, retorna
        if(getNode(cur)== null) return;

        //se não, continua esse processo até retornar
        strList.add(getNode(cur));
        setNode(cur, null); 

        searchAux(strList, nodeLeft(cur));
        searchAux(strList, nodeRight(cur));
    }

}