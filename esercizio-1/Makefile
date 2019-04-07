# lista dei target da eseguire sempre
usage:
	@echo "Uso:\n"\
	      "- make arraylist: esegue i test con l'implementazione con array ridimensionabili\n"\
				"- make linkedlist: esegue i test con l'implementazione con record collegati\n"\
				"- make main1: test della funzione merge con arraylist\n"\
				"- make main2: test della funzione merge con linkedlist\n"

# compila (grazie alla dipendenza) e esegue i test
arraylist: bin/list_array_tests
	bin/list_tests

linkedlist: bin/list_linked_tests
	bin/list_tests

main1: bin/list_array_main
	bin/list_main

main1: bin/list_linked_main
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
