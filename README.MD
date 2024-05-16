# Binary Trees using Arrays

## Project description

This project implements binary trees and balanced binary trees (AVL) in Java, using only arrays (vectors). The goal is to provide an in-depth understanding of how these data structures work and are manipulated in practice.

## Classes and Subclasses

### Base Class: ArvBin

The `ArvBin` class represents an unbalanced binary search tree, implemented using an array of Strings in the format of a heap.

#### Public Interface

- `boolean find(String v)`: Checks if the element is present in the tree.
- `void insert(String value)`: Inserts a string into the tree.
- `int len()`: Returns the number of elements present in the tree.
- `boolean remove(String v)`: Removes an element from the tree.
- `String toString()`: Returns a representation of the tree in string format.

#### Builders

- `ArvBin(int len)`: Constructor that generates an empty tree.

### Subclasses: ArvBal and ArvAVL

#### ArvBal

The class `ArvBal` is a subclass of `ArvBin` that represents a perfectly balanced binary search tree.

#### ArvAVL

The class `ArvAVL` is a subclass of `ArvBin` that represents an AVL tree, a type of self-balancing binary search tree.

### Protected Methods in the Superclass

- `protected int countNodes(int i)`: Counts the number of nodes in a subtree.
- `protected String getNode(int i)`: Returns the value of a given node.
- `protected boolean isBalanced()`: Checks if the tree is balanced.
- `protected int nodeLeft(int i)`: Returns the index of the left child of a node.
- `protected int nodeRight(int i)`: Returns the index of the right-hand child of a node.
- `protected void setNode(int i, String v)`: Assigns a value to a given node.

### Example of the `toString()` Method

The `toString()` method returns the representation of the tree in the format of a graph from the Graphviz tool:

```
digraph {
     "0 jose" -> "1 joao"
     "0 jose" -> "2 maria"
     "1 joao" -> "4 joao"
     "2 maria" -> "6 mario"
     "6 mario" -> "14 pedro"
}
```

## Example Entrance exit

### Entry

- `i jose`: Inserts "jose" into the tree.
- `i joao`: Inserts "joao" into the tree.
- `i maria`: Inserts "maria" into the tree.
- `d joao`: Removes "joao" from the tree.
- `i mario`: Inserts "mario" into the tree.

### Exit

The `toString()` method is called for each of the trees after the insertion and removal operations.

## Tests

The project includes a `runTests.sh` script and a `Makefile` file to facilitate running tests and compiling the project.

### Running the Tests

1. Give permission to the test script:

     ```bash
     sudo chmod +x runTests.sh
     ```

2. Run the tests:

     ```bash
     ./runTests.sh
     ```


3. Or Compile manually and run the program using the Makefile:

     ```bash
     make
     make run
     ```


4. To clean the generated files:

     ```bash
     make clean
     ```




Project developed for the [Object Oriented Programming](https://uspdigital.usp.br/jupiterweb/obterDisciplina?sgldis=SSC0103&codcur=55041&codhab=0) course at the Institute of Mathematics and Computing, University of São Paulo.