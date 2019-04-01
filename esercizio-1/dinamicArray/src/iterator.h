#pragma once

// Definition of the opaque type
typedef struct _Iterator Iterator;

/*
   Returns a new allocated iterator of list
   list: container to iterate
 */
Iterator *Iterator_create(List *l);

// Frees the memory allocated for the iterator (the list remains untouched) 
void Iterator_destroy(Iterator *it);

// Check if the iterator is valid
int Iterator_valid(Iterator *it);

// Get the element in currently indexed
void *Iterator_get(Iterator *it);

// Move the index of the iterator
void Iterator_next(Iterator *it);