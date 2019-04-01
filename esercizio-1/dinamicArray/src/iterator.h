#pragma once
#ifndef _ITERATOR_H_
#define _ITERATOR_H_

// Definition of the opaque type
typedef struct _Iterator Iterator;

/*
   Returns a new allocated iterator of list
   list: container to iterate
 */
Iterator *Iterator_create(List *l);

// Frees the memory allocated for the iterator (the list remains untouched) 
void Iterator_destroy(Iterator *it);

// Checks if the iterator is valid
int Iterator_valid(Iterator *it);

// Gets the element in currently indexed
void *Iterator_get(Iterator *it);

// Moves the index of the iterator
void Iterator_next(Iterator *it);

#endif