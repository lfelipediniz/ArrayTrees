class ABBVetor:
    def __init__(self):
        self.tree = [None]  # Inicializa com None para facilitar o cálculo de índices

    def insert(self, value):
        if self.tree == [None]:
            self.tree.append(value)
            return
        self.tree.append(value)
        self._sift_up(len(self.tree) - 1)

    def _sift_up(self, index):
        while index > 1 and self.tree[index] < self.tree[index // 2]:
            self.tree[index], self.tree[index // 2] = self.tree[index // 2], self.tree[index]
            index = index // 2

    def find(self, value):
        index = 1
        while index < len(self.tree):
            if self.tree[index] == value:
                return True
            elif value < self.tree[index]:
                index = 2 * index
            else:
                index = 2 * index + 1
        return False

    def delete(self, value):
        # A implementação de remoção é mais complexa e depende das necessidades específicas
        pass

    def display(self):
        # Exibe o vetor da árvore
        print(self.tree[1:])  # Ignora o primeiro elemento None

# Uso da classe
abb = ABBVetor()
abb.insert(10)
abb.insert(5)
abb.insert(15)
abb.insert(3)
abb.display()  # Mostra a árvore
print(abb.find(15))  # Deve retornar True
print(abb.find(99))  # Deve retornar False
