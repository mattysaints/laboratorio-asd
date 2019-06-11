# lista dei target da eseguire sempre
run:
	@echo "Esercizio 1. Uso:\n"\
	      "   make test   \ttest libreria 'list.c'\n"\
				"   make usage   \ttest funzione 'merge' implementata con 'list.c'"

# compila (grazie alla dipendenza) e esegue i test
test: bin/list_tests
	bin/list_tests

usage: bin/list_main
	bin/list_main

# Flags per il compilatore
CFLAGS = -g -Wall -Wpedantic -Wno-padded -Werror

# Lista degli header files
INCLUDES = src/*.h

# Tutti i target vanno ricompilati se cambiano gli header o il makefile
COMMON_DEPS = $(INCLUDES) Makefile

# compilazione file *.o
build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/list_tests: build/list_tests.o build/list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/list.o build/unity.o

bin/list_main: build/list_main.o build/list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_main build/list_main.o build/list.o build/unity.o

# elimina i file eseguibili e oggetto
clean:
	rm -f build/* bin/*
