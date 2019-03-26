#pragma once

#include <stddef.h>

// Definition of the opaque type
typedef struct _List List;

/*
   Returns a new allocated list
   length: given initial length of the list
 */
List *List_create(size_t length);

// Returns 0 if the argument type of list is empty, 1 otherwise
int List_empty(List *l);

// Inserts an element to the list at the end of the queue
void List_insert(List *l, void *elem);