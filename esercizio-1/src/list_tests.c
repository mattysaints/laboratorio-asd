#include "list.h"
#include "unity.h"
#include "unity_internals.h"

#include <stdlib.h>
#include <stdlib.h>
#include <string.h>


static int *new_int(int num) {
  int *num_ptr = (int *)malloc(sizeof(int));
  *num_ptr = num;
  return num_ptr;
}

static List *build_list_int() {
  List *l = List_create(4);

  List_add(l,new_int(3));
  List_add(l,new_int(8));
  List_add(l,new_int(2));
  return l;
}

static List *build_list_string() {
  List *l = List_create(4);

  List_add(l,"String 1");
  List_add(l,"String 2");
  List_add(l,"String 3");
  return l;
}


// Test List of integers

static void test_list_new_not_null() {
  List *l = build_list_int();

  TEST_ASSERT_NOT_NULL(l);
  List_destroy(l);
}

static void test_list_destroy() {
  List *l = build_list_int();
  List_destroy(l);

  TEST_ASSERT_EQUAL_INT(1,1);
}

static void test_list_empty() {
  List *l = List_create(7);

  TEST_ASSERT_EQUAL_INT(1,List_empty(l));
  List_destroy(l);
}

static void test_list_size() {
  List *l = build_list_int();

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
  List *l = build_list_int();

  List_add_index(l,new_int(10),1);
  TEST_ASSERT_EQUAL_INT(4,List_size(l));
  TEST_ASSERT_EQUAL_INT(10,*(int *)List_get(l,1));
  List_destroy(l);
}

static void test_list_del() {
  List *l = build_list_int();

  List_del(l);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  List_destroy(l);
}

static void test_list_del_index() {
  List *l = build_list_int();

  List_del_index(l,1);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  TEST_ASSERT_EQUAL_INT(2,*(int *)List_get(l,1));
  List_destroy(l);
}

static void test_list_get() {
  List *l = build_list_int();

  TEST_ASSERT_EQUAL_INT(3,*(int *)List_get(l,0));
}


// Test List of strings

static void test_list_add_string() {
  List *l = List_create(5);

  List_add(l,"This is a string");
  TEST_ASSERT_EQUAL_INT(1,List_size(l));
  TEST_ASSERT_EQUAL_STRING("This is a string",(char *)List_get(l,0));
  List_destroy(l);
}

static void test_list_add_index_string() {
  List *l = build_list_string();

  List_add_index(l,"New string",1);
  TEST_ASSERT_EQUAL_INT(4,List_size(l));
  TEST_ASSERT_EQUAL_INT("New string",(char *)List_get(l,1));
  List_destroy(l);
}

static void test_list_del_string() {
  List *l = build_list_string();

  List_del(l);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  List_destroy(l);
}

static void test_list_del_index_string() {
  List *l = build_list_string();

  List_del_index(l,1);
  TEST_ASSERT_EQUAL_INT(2,List_size(l));
  TEST_ASSERT_EQUAL_INT("String 3",(char *)List_get(l,1));
  List_destroy(l);
}

static void test_list_get_string() {
  List *l = build_list_string();

  TEST_ASSERT_EQUAL_INT("String 1",(char *)List_get(l,0));
}


// Test Iterator

static void test_iterator_new_not_null() {
  List *l = List_create(3);
  Iterator *it = Iterator_create(l);

  TEST_ASSERT_NOT_NULL(it);
  Iterator_destroy(it);
  List_destroy(l);
}

static void test_iterator_destroy() {
  List *l = List_create(3);
  Iterator *it = Iterator_create(l);

  Iterator_destroy(it);
  List_destroy(l);

  TEST_ASSERT_EQUAL_INT(1,1);
}

static void test_iterator_valid() {
  List *l = build_list_int();
  Iterator *it = Iterator_create(l);
  int i;

  for(i=0; i<List_size(l); i++) {
    TEST_ASSERT_EQUAL_INT(1,Iterator_valid(it));
    Iterator_next(it);
  }
  TEST_ASSERT_EQUAL_INT(0,Iterator_valid(it));
  
  Iterator_destroy(it);
  List_destroy(l);
}

static void test_iterator_get() {
  List *l = build_list_int();
  Iterator *it = Iterator_create(l);

  TEST_ASSERT_EQUAL_INT(3,*(int *)Iterator_get(it));
  Iterator_next(it);
  TEST_ASSERT_EQUAL_INT(8,*(int *)Iterator_get(it));
  Iterator_next(it);
  TEST_ASSERT_EQUAL_INT(2,*(int *)Iterator_get(it));
  
  Iterator_destroy(it);
  List_destroy(l);
}

static void test_iterator_next() {
  List *l = List_create(2);
  List_add(l,new_int(4));

  Iterator *it = Iterator_create(l);

  TEST_ASSERT_EQUAL_INT(1,Iterator_valid(it));
  Iterator_next(it);
  TEST_ASSERT_EQUAL_INT(0,Iterator_valid(it));

  Iterator_destroy(it);
  List_destroy(l);
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
  RUN_TEST(test_list_add_string);
  RUN_TEST(test_list_add_index_string);
  RUN_TEST(test_list_del_string);
  RUN_TEST(test_list_del_index_string);
  RUN_TEST(test_list_get_string);
  RUN_TEST(test_iterator_new_not_null);
  RUN_TEST(test_iterator_destroy);
  RUN_TEST(test_iterator_valid);
  RUN_TEST(test_iterator_get);
  RUN_TEST(test_iterator_next);
  UNITY_END();
}