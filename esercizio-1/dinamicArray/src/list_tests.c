#include "list.h"
#include "unity.h"
#include "unity_internals.h"

#include <stdlib.h>

// Definition of the opaque type
typedef struct _List List;


static int *new_int(int num) {
  int *num_ptr = (int *)malloc(sizeof(int));
  *num_ptr = num;
  return num_ptr;
}

static List *build_list() {
  List *l = List_create(4);

  List_insert(l,new_int(3));
  List_insert(l,new_int(8));
  List_insert(l,new_int(2));
  return l;
}

static void test_list_new_not_null() {
  List *l = build_list();

  TEST_ASSERT_NOT_NULL(l);
}

static void test_list_empty() {
  List *l = List_create(7);

  TEST_ASSERT_EQUAL_INT(1,List_empty(l));
}


int main() {
  UNITY_BEGIN();
  RUN_TEST(test_list_new_not_null);
  RUN_TEST(test_list_empty);
  UNITY_END();
}