#include "list.h"
#include "iterator.h"

struct _Iterator {
  List *list;
  int index;
};

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
  return it && it->index < it->list->size;
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