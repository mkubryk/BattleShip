# Répertoires
SRC_DIR = src
BIN_DIR = bin

# Trouver tous les fichiers .java dans le répertoire src et ses sous-répertoires
SOURCES = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

all: compile

# Compilation
compile:
	@echo "Compilation des fichiers sources Java..."
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES) || (echo "Erreur lors de la compilation !" && exit 1)
	@echo "Compilation réussie."

# Exécuter Test avec un fichier d'input 
testFile: compile
	@echo "Lancement de la classe de test..."
	java -cp $(BIN_DIR) test.Test < input-test.txt

# Exécuter Test
test: compile
	@echo "Lancement de la classe de test..."
	java -cp $(BIN_DIR) test.Test 

# Exécuter le programme principal
run: compile
	@echo "Lancement du jeu de bataille navale..."
	java -cp $(BIN_DIR) batailleNavale.Main

# Nettoyage
clean:
	@echo "Suppression des fichiers compilés..."
	rm -rf $(BIN_DIR)/*.class
	@echo "Fichiers compilés supprimés."

# Guide des commandes
help:
	@echo "  make           Compile les fichiers sources Java."
	@echo "  make testFile      Compile et exécute la classe de src/test/Test.java avec un fichier d'input à lire"
	@echo "  make test      Compile et exécute la classe de src/test/Test.java."
	@echo "  make run       Compile et exécute le programme principal (classe src/batailleNavale/Main.java)."
	@echo "  make clean     Supprime les fichiers compilés."
	@echo "  make help      Guide des commandes."
