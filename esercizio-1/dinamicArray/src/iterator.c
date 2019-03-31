#include <list.c>
#define TRUE 1
#define FALSE 0

struct _Iterator {
  List *Iterator;
  int index;
};

Iterator *Iterator_create(List *l) {
	Iterator *it = (*Iterator)malloc(sizeof(Iterator));
	it->Iterator = l;
	it->index = 0;
}

void Iterator_destroy(Iterator *it) {
  if(Iterator_isValid(it)) {
    List_destroy(it->Iterator);
    free(it);
  }
}

int Iterator_isValid(Iterator *it) {
	if(it && (it->iterator)) {
		return TRUE;
	}else {
		return FALSE;
	}
}

void *Iterator_getElem(Iterator *it) {
	if(Iterator_isValid(it)) {
		return List_get(it->iterator,it->index);
	}else{
		return NULL;
	}
}

void Iterator_getNext(Iterator *it){
	if(Iterator_isValid(it)) {
		it->iterator = *(iterator)->array[it->index++];
		it->index++;
	}
}