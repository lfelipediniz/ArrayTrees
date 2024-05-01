JCC = javac
JVM = java
MAIN = Main

# Definir arquivos fonte
SOURCES = ArvAVL.java ArvBal.java ArvBin.java Main.java

# Definir arquivos .class correspondentes
CLASSES = $(SOURCES:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JCC) $<

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class

.PHONY: all run clean
