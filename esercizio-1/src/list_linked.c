#include "list.h"

#include <stddef.h>
#include "list.h"
#include <stdlib.h>

struct _List {
  struct Node *top;
  struct Node *rear;
  int size;
};

struct Node {
	void *elem;
	struct Node *next;
	struct Node *prev;
}

struct _Iterator {
  List *list;
  int index;
};


// List functions

List *List_create() {
  List *result = (List *)malloc(sizeof(List));
  result->top = NULL;
  result->rear = NULL;
  result->size = 0;
  return result;
}

void List_destroy(List *l) {
  if(l) {
    free(l->array);
    free(l);
  }
}

int List_empty(List *l) {
  return l && l->size==0;
}

int List_size(List *l) {
  if(l)
    return l->size;
  else
    return -1;
}

void List_add(List *l, void *elem) {
  if(l) {
  	if(List_empty(l)) {
  		l->top =(Node *)malloc(sizeof(struct Node));
  		l->rear = l->top;
  		l->top->elem = elem;
  		l->top->next = NULL;
  		l->top->prev = NULL;
  	} else {
  		Node * temp = l->rear =(Node *)malloc(sizeof(struct Node));
  		Node * previus = l->rear;
  		l->rear->next = temp;
  		l->elem = elem;
  		l->rear->prev = previus;
 			l->top->next = l->rear;
  	}
  }
}

void List_add_index(List *l, void *elem, int index) {
	if(l && index<l->size && index>=0) {
		if(l->size >= l->length) {
      l->length *= 2;
      l->array = (void**)realloc(l->array,sizeof(void*)*l->length);
    }
		int i;
		for(i=l->size; i>index; i--)
			l->array[i] = l->array[i-1];
		l->array[index] = elem;
		l->size++;
	}
}

