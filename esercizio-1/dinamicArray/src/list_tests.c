#include "list.h"
#include "iterator.h"
#include "unity.h"
#include "unity_internals.h"

#include <stdlib.h>

// Definition of the opaque type
typedef struct _List List;
typedef struct _Iterator Iterator;


static int *new_int(int num) {
  int *num_ptr = (int *)malloc(sizeof(int));
  *num_ptr = num;
  return num_ptr;
}

static List *build_list() {
  List *l = List_create(4);

  List_add(l,new_int(3));
  List_add(l,new_int(8));
  List_add(l,new_int(2));
  return l;
}

static void test_list_new_not_null() {
  List *l = build_list();

  TEST_ASSERT_NOT_NULL(l);
  List_destroy(l);
}

static void test_list_destroy() {
  List *l = build_list();
  List_destroy(l);

  TEST_ASSERT_EQUAL_INT(1,1);
}

static void test_list_empty() {
  List *l = List_create(7);

  TEST_ASSERT_EQUAL_INT(1,List_empty(l));
  List_destroy(l);
}

static void test_list_size() {
  List *l = build_list();

  TEST_ASSERT_EQUAL_INT(3,List_size(l));
  List_destroy(l);
}

static void test_list_add() {
  List *l = List_create(5);

  List_add(l,new_int(4));
  TEST_ASSERT_EQUAL_INT(1,List_size(l));
  TEST_ASSERT_EQUAL_INT(4,*(int *)List_get(l,0));
  List_destroy(l);
}

static void test_list_add_index() {
  List *l = build_list();

  List_add_index(l,new_int(10),1);
  TEST_ASSERT_EQUAL_INT(4,List_size(l));
  TEST_ASSERT_EQUAL_INT(10,*(int *)List_get(l,1));
  List_destroy(l);
}

static void test_list_del() {
  List *l = build_list();

  List_del(l);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  List_destroy(l);
}

static void test_list_del_index() {
  List *l = build_list();

  List_del_index(l,1);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  TEST_ASSERT_EQUAL_INT(2,*(int *)List_get(l,1));
  List_destroy(l);
}

static void test_list_get() {
  List *l = build_list();

  TEST_ASSERT_EQUAL_INT(3,*(int *)List_get(l,0));
}

static Iterator *build_iterator(List *l) {
  Iterator *it = Iterator_create(l);
  return it;
}

static void test_list_new_not_null() {
  Iterator *it = build_iterator(l);

  TEST_ASSERT_NOT_NULL(it);
  List_destroy(it);
}


int main() {
  UNITY_BEGIN();
  RUN_TEST(test_list_new_not_null);
  RUN_TEST(test_list_destroy);
  RUN_TEST(test_list_empty);
  RUN_TEST(test_list_size);
  RUN_TEST(test_list_add);
  RUN_TEST(test_list_add_index);
  RUN_TEST(test_list_del);
  RUN_TEST(test_list_del_index);
  RUN_TEST(test_list_get);
  UNITY_END();
}