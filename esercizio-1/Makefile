# lista dei target da eseguire sempre
usage:
	@echo "Esercizio 1. Uso:\n"\
	      "   make test_arraylist   \ttest List (array ridimensionabili)\n"\
				"   make test_linkedlist   \ttest List (record collegati)\n"\
				"   make test_merge_al   \ttest funzione 'merge' (array ridimensionabili)\n"\
				"   make test_merge_ll   \ttest funzione 'merge' (record collegati)\n"

# compila (grazie alla dipendenza) e esegue i test
test_arraylist: bin/list_array_tests
	bin/list_tests

test_linkedlist: bin/list_linked_tests
	bin/list_tests

test_merge_al: bin/list_array_main
	bin/list_main

test_merge_ll: bin/list_linked_main
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

bin/list_array_tests: build/list_tests.o build/list_array.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/list_array.o build/unity.o

bin/list_linked_tests: build/list_tests.o build/list_linked.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/list_linked.o build/unity.o

bin/list_array_main: build/list_main.o build/list_array.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_main build/list_main.o build/list_array.o build/unity.o

bin/list_linked_main: build/list_main.o build/list_linked.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_main build/list_main.o build/list_linked.o build/unity.o

# elimina i file eseguibili e oggetto
clean:
	rm -f build/* bin/*
