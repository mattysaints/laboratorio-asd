#pragma once

#include <stddef.h>

// Definition of the opaque type
typedef struct _List List;

/*
   Returns a new allocated list
   length: given initial length of the list
 */
List *List_create(size_t length);

// Frees the memory allocated for the list l
void List_destroy(List *l);

// Returns 0 if the argument type of list is empty, 1 otherwise
int List_empty(List *l);

// Returns the number of elements in the list
int List_size(List *l);

// Inserts an element to the list at the end of the queue
void List_add(List *l, void *elem);

// Inserst an element to the list in the position spefified by index
void List_add_index(List *l, void *elem, int index);

// Deletes the element located ad the end of the queue
void List_del(List *l);

// Deletes the element located at index
void List_del_index(List *l, int index);

// Returns the element located in the position specfied by index
void *List_get(List *l, int index);
