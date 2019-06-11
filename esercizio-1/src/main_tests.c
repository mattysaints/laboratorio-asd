#include "list.h"
#include "main.h"
#include "unity.h"
#include "unity_internals.h"

#include <stdlib.h>
#include <stdlib.h>
#include <string.h>


// Order criteria for int and strings

static int compare_int(void *elem1, void *elem2) {
  if(*(int *)elem1 < *(int *)elem2)
    return -1;
  else if(*(int *)elem1 == *(int *)elem2)
    return 0;
  else
    return 1;
}

static int compare_string(void *elem1, void *elem2) { 
  int result = strcmp((char *)elem1, (char *)elem2);
  if(result < 0)
    return -1;
  else if(result == 0)
    return 0;
  else
    return 1;
}


// Support functions

static int *new_int(int num) {
  int *num_ptr = (int *)malloc(sizeof(int));
  *num_ptr = num;
  return num_ptr;
}

static List *build_list_int() {
  List *l = List_create(4);

  List_add(l,new_int(2));
  List_add(l,new_int(3));
  List_add(l,new_int(8));
  return l;
}

static List *build_list_int_same() {
  List *l = List_create(4);

  List_add(l,new_int(2));
  List_add(l,new_int(2));
  List_add(l,new_int(3));
  List_add(l,new_int(3));
  List_add(l,new_int(8));
  List_add(l,new_int(8));
  return l;
}

static List *build_list_int_v2() {
  List *l = List_create(4);

  List_add(l,new_int(1));
  List_add(l,new_int(6));
  List_add(l,new_int(13));
  return l;
}

static List *build_list_int_expected() {
  List *l = List_create(4);

  List_add(l,new_int(1));
  List_add(l,new_int(2));
  List_add(l,new_int(3));
  List_add(l,new_int(6));
  List_add(l,new_int(8));
  List_add(l,new_int(13));
  return l;
}

static List *build_list_string() {
  List *l = List_create(4);

  List_add(l,"Apple");
  List_add(l,"Cat");
  List_add(l,"Lion");
  return l;
}

static List *build_list_string_v2() {
  List *l = List_create(4);

  List_add(l,"Boat");
  List_add(l,"Mother");
  List_add(l,"Pencil");
  return l;
}

static List *build_list_string_expected() {
  List *l = List_create(4);

  List_add(l,"Apple");
  List_add(l,"Boat");
  List_add(l,"Cat");
  List_add(l,"Lion");
  List_add(l,"Mother");
  List_add(l,"Pencil");
  return l;
}

// Tests

static void test_list_equals_int(List *l1, List *l2) {
  if(l1 && l2) {
    Iterator *it1 = Iterator_create(l1);
    Iterator *it2 = Iterator_create(l2);
    while(Iterator_valid(it1) && Iterator_valid(it2)) {
      TEST_ASSERT_EQUAL_INT(*(int *)Iterator_get(it1), *(int *)Iterator_get(it2));
      Iterator_next(it1);
      Iterator_next(it2);
    }
    TEST_ASSERT_EQUAL_INT(Iterator_valid(it1), Iterator_valid(it2));
    Iterator_destroy(it1);
    Iterator_destroy(it2);
  }
}

static void test_list_equals_string(List *l1, List *l2) {
  if(l1 && l2) {
    Iterator *it1 = Iterator_create(l1);
    Iterator *it2 = Iterator_create(l2);
    while(Iterator_valid(it1) && Iterator_valid(it2)) {
      TEST_ASSERT_EQUAL_STRING(Iterator_get(it1), Iterator_get(it2));
      Iterator_next(it1);
      Iterator_next(it2);
    }
    TEST_ASSERT_EQUAL_INT(Iterator_valid(it1), Iterator_valid(it2));
    Iterator_destroy(it1);
    Iterator_destroy(it2);
  }
}

static void test_merge_null() {
  TEST_ASSERT_NULL(merge(NULL, List_create(3), List_create(3)));
  TEST_ASSERT_NULL(merge(compare_int, NULL, List_create(3)));
  TEST_ASSERT_NULL(merge(compare_int, List_create(3), NULL));
  TEST_ASSERT_NULL(merge(compare_int, NULL, NULL));
}

static void test_merge_one_empty() {
  List *l1 = build_list_int();
  List *l2 = List_create(3);
  List *merged = merge(compare_int,l1,l2);
  
  test_list_equals_int(l1,merged); 

  List_destroy(l1);
  List_destroy(l2);
  List_destroy(merged);
}

static void test_merge_same_elements() {
  List *l1 = build_list_int();
  List *expected = build_list_int_same();
  List *merged = merge(compare_int,l1,l1);

  test_list_equals_int(expected,merged);

  List_destroy(l1);
  List_destroy(expected);
  List_destroy(merged);
}

static void test_merge_int() {
  List *l1 = build_list_int();
  List *l2 = build_list_int_v2();
  List *expected = build_list_int_expected();

  test_list_equals_int(expected, merge(compare_int,l1,l2));

  List_destroy(l1);
  List_destroy(l2);
  List_destroy(expected);
}

static void test_merge_string() {
  List *l1 = build_list_string();
  List *l2 = build_list_string_v2();
  List *expected = build_list_string_expected();

  test_list_equals_string(expected, merge(compare_string,l1,l2));
  List_destroy(l1);
  List_destroy(l2);
  List_destroy(expected);
}

int main() {
  UNITY_BEGIN();
  RUN_TEST(test_merge_null);
  RUN_TEST(test_merge_one_empty);
  RUN_TEST(test_merge_same_elements);
  RUN_TEST(test_merge_int);
  RUN_TEST(test_merge_string);
  UNITY_END();
}