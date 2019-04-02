#include "list.h"

#include <stddef.h>
#include <stdlib.h>

struct _List {
  void** array;
  int size;
  int length;
};

struct _Iterator {
  List *list;
  int index;
};


// List funcions

List *List_create(int length) {
  if(length>0) {
    List *result = (List *)malloc(sizeof(List));
    result->array = (void**)malloc(sizeof(void*)*length);
    result->size = 0;
    result->length = length;
    return result;
  } else
    return NULL;
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
    if(l->size >= l->length) {
      l->length *= 2;
      l->array = (void**)realloc(l->array,sizeof(void*)*l->length);
    }
    l->array[l->size] = elem;
    l->size++;
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

void List_del(List *l) {
  if(l && !List_empty(l))
    l->size--;
}

void List_del_index(List *l, int index) {
  if(l && !List_empty(l) && index<l->size && index>=0) {
    int i;
    for(i=index; i<l->size; i++) 
      l->array[i] = l->array[i+1];
    l->size--;
  }
}

void *List_get(List *l, int index) {
  if(l && index<l->size && index>=0)
    return l->array[index];
  else
    return NULL;
}


// Iterator functions

Iterator *Iterator_create(List *l) {
  if(l) {
    Iterator *it = (Iterator *)malloc(sizeof(Iterator));
    it->list = l;
    it->index = 0;
    return it;
  } else
    return NULL;
}

void Iterator_destroy(Iterator *it) {
  if(it)
    free(it);
}

int Iterator_valid(Iterator *it) {
  return it && it->index<it->list->size;
}

void *Iterator_get(Iterator *it) {
  if(it && Iterator_valid(it))
    return List_get(it->list,it->index);
  else
    return NULL;
}

void Iterator_next(Iterator *it){
  if(it && Iterator_valid(it))
    it->index++;
}