#include "list.h"

#include <stddef.h>
#include <stdlib.h>
#include <string.h>

List *merge(int(*compare)(List *l1, List *l2)) {
	List *result = List_create(List_size(l1)+List_size(l2));
	Iterator *it1 = Iterator_create(l1);
	Iterator *it2 = Iterator_create(l2);

	while(Iterator_valid(it1) && Iterator_valid(it2)) {
		if(compare(Iterator_get(it1), Iterator_get(it2)) <= 0) {
			List_add(result, Iterator_get(it1));
			Iterator_next(it1);
		}	else {
			List_add(result, Iterator_get(it2));
			Iterator_next(it2);
		}
	}

	while(Iterator_valid(it1)) {
		List_add(result, Iterator_get(it1));
		Iterator_next(it1);
	}

	while(Iterator_valid(it2)) {
		List_add(result, Iterator_get(it2));
		Iterator_next(it2);
	}
	return result;
}

int compare_int(void *elem1, void *elem2) {
  int *e1 = (int*) elem1;
  int *e2 = (int*) elem2;

  if(*(e1) < *(e2) ) {
    return -1;
  }
  if(*(e1) > *(e2) ) {
    return 1;
  }
  return 0;
}

int compare_str(void *elem1, void *elem2) { 
  char *e1 = (char*) elem1;
  char *e2 = (char*) elem2;

  int result = strcmp(e1, e2);

  if(result < 0) {
    return -1;
  }
  if(result > 0) {
    return 1;
  }
  return 0;
}

int main() {
  return 0;
}

