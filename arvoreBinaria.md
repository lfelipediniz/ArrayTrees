# Exercício Herança

Faça em Java

## Árvore Binária

### Classe base

Implemente uma classe
que representa uma
árvore de busca binária,
sem balanceamento.

### Implementação

Essa classe deve ser
implementada usando um
array de Strings, no
formato de um heap.

### Heap

Posição 0 do array é a
raiz. Filho esquerdo do nó i
está na posição (2 _ i + 1).
Filho direito na posição
(2_ i + 2).

### Interface pública

Inserir, remover, procurar,
tamanho, toString

---

## Obrigatório

### Resumo do Método

| Modifierer and Type | Method               | Description                                                     |
| ------------------- | -------------------- | --------------------------------------------------------------- |
| boolean             | find(String v)       | Verifica se o elemento está presente                            |
| void                | insert(String value) | Insere uma string na árvore                                     |
| int                 | len()                | retorna o número de elementos presentes na árvore              |
| boolean             | remove(String v)     | Remove um elemento da árvore                                    |
| String              | toString()           | retorna uma string da árvore |

### Resumo dos Contrutores

| Constructor     | Description                      |
| --------------- | -------------------------------- |
| ArvBin(int len) | contructor gera uma árvore vazia |

## Árvore Binária Balanceada

### Subclasses

Implementa uma subclasse que representa uma árvore de busca binária, com balanceamento perfeito.

### Subclasses

Implementa uma classe que representa uma árvore AVL

### Heap

Serão necessários métodos protegidos na superclasse para masnipular a heap.

### Interface Pública

Inserir, remover, procurar, tamanho, string

## Subclasses

### Class ArvAVL

```
java.lang.Object
    ArvBin
            ArvAVL
```
public class arvAVL extends ArvBin

### Class ArvBal

```
java.lang.Object
    ArvBin
            ArvBal
```
public class arvBal extends ArvBin

## Protegigidos - superclasse

| Modifierer and Type | Method               | Description                                                     |
| ------------------- | -------------------- | --------------------------------------------------------------- |
| protected int         | countNodes(int i)   | Conta o número de nós em uma subárvore
| boolean     | find(String) | Verifica se o elemento está presente na árvore
| protected String     | getNode(int i) | Retorna o valor de um dado nó
| void    | insert(String value) | Insere uma string na árvore
| protect boolean    | isBalence() | Verifica se a árvore está balanceada, de acordo com o critério de cada tipo de árvore
| int    | len() | Retorna o númeor de elementos presentes na árvore
| protected int    | nodeLeft(int i) |Indica qual é o númeor do filho à esquerda da posição i
| protected int    | nodeRight(int i) |Indica qual é o númeor do filho à direita da posição i
| boolean | remove(String v) | Remove o elemento da árvore
| protected void    | setNode(int i, String v) | Atribui um valor para um determinado nó
| String  | toString() | Retorna uma string no formato de uma grafo da ferramenta graphviz

## toString()
```
disgraph {
    "0 jose" -> "1 joao"
    "0 jose" -> "2 maria"
    "1 joao" -> "4 joao"
    "2 maria" -> "6 mario"
    "6 mario" -> "14 pedro"
}
```

## Entrada/Saída

### Entrada
i jose
i joao
i maria
i jose
d joao
i mario

### Saída
toString() para cada uma das árvores








