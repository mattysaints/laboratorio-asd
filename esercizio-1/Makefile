
# lista dei target da eseguire sempre
usage:
	@echo "Uso:\n"\
	      "- make arraylist: esegue i test con l'implementazione con array ridimensionabili\n"\
				"- make linkedlist: esegue i test con l'implementazione con record collegati\n"

# compila (grazie alla dipendenza) e esegue i test
arraylist: bin/arraylist_tests
	bin/list_tests

linkedlist: bin/linkedlist_tests
	bin/list_tests

# Flags per il compilatore
CFLAGS = -g -Wall -Wpedantic -Wno-padded -Werror

# Lista degli header files
INCLUDES = src/*.h

# Tutti i target vanno ricompilati se cambiano gli header o il makefile
COMMON_DEPS = $(INCLUDES) Makefile

# compilazione file *.o
build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

build/arraylist.o: arraylist/list.c
	$(CC) $(CFLAGS) -c build/arraylist.o

build/linkedlist.o: linkedlist/list.c
	$(CC) $(CFLAGS) -c build/linkedlist.o

bin/arraylist_tests: build/list_tests.o build/arraylist.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/arraylist.o build/unity.o

bin/linkedlist_tests: build/list_tests.o build/linkedlist.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/linkedlist.o build/unity.o

clean:
	rm -f build/* bin/*
