JCC = javac
JVM = java
MAIN = Main

# definir arquivos fonte
SOURCES = ArvAVL.java ArvBal.java ArvBin.java Main.java

# definir arquivos .class correspondentes
CLASSES = $(SOURCES:.java=.class)

all: $(CLASSES)

%.class: %.java 

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class

.PHONY: all run clean
