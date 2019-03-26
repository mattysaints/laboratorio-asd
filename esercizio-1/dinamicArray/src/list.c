#import <stddef.h>

struct _List {
  void** array;
  size_t size;
  size_t length;
};

List *List_create(size_t bytes) {
  if(bytes>0) {
    List *result = (List *)malloc(sizeof(List));
    result->array = (void**)malloc(sizeof(void*)*bytes);
    result->size = 0;
    result->length = 1;
    return result;
  }
}

int List_empty(List *l) {
  if(l)
    return l->size==0;
}

void List_insert(List *l) {
  if(l) {
    if(l->size>=l->length) {

    }
  }
}