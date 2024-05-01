# compilador Java
JAVAC = javac

# interpretador Java
JAVA = java

# lista de arquivos fonte
SOURCES = ArvAVL.java ArvBal.java arvBin.java Main.java

# arquivos de classe que serão criados (mesmos nomes que os .java, mas com .class)
CLASSES = $(SOURCES:.java=.class)

# target padrão que será executado quando não for especificado um na linha de comando
default: run

# compilar os arquivos .java em .class
$(CLASSES): $(SOURCES)
	$(JAVAC) $(SOURCES)

# executar a classe principal
run: $(CLASSES)
	$(JAVA) Main

# limpar os arquivos .class, mantendo o diretório limpo
clean:
	rm -f *.class

# target para ajudar na depuração do Makefile (mostra as variáveis)
debug:
	@echo "SOURCES: $(SOURCES)"
	@echo "CLASSES: $(CLASSES)"
