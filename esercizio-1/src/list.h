#pragma once
#ifndef _LIST_H_
#define _LIST_H_

/*
   LIST IMPLEMENTATION:

   This library contains two different implementations of the data structure list of generic type:
    - list implemented with a dinamic array
    - list implemented with linked records

    Leaving the following #define untouched means that the implementation with dinamic arrays will be used
    Otherwise, by commenting the #define, the library will consider the implementation with linked records 
 */
#define DYNAMIC_ARRAY

/*
   Definition of types and functions
 */

// Definition of the opaque types
typedef struct _List List;
typedef struct _Iterator Iterator;


/*
   Returns a new allocated empty list
*/
List *List_create();

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


// Iterator Funcitons

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
