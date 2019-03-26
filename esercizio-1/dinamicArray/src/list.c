#include "list.h"

#include <stddef.h>
#include <stdlib.h>

struct _List {
  void** array;
  size_t size;
  size_t length;
};

List *List_create(size_t length) {
  if(length>0) {
    List *result = (List *)malloc(sizeof(List));
    result->array = (void**)malloc(sizeof(void*)*length);
    result->size = 0;
    result->length = length;
    return result;
  } else
    return NULL;
}

int List_empty(List *l) {
    return l && l->size==0;
}

void List_insert(List *l, void *elem) {
  if(l) {
    if(l->size >= l->length) {
      l->length *= 2;
      l->array = (void**)realloc(l->array,sizeof(void*)*l->length);
    }
    l->array[l->size] = elem;
    l->size++;
  }
}