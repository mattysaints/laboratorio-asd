#ifndef _MAIN_H_
#define _MAIN_H_

/*
   Returns an ordered list which is the union of the two parameter lists
   compare: function pointer to a user defined order criterion that will determine the order of the output list
   l1: ordered list to join 
   l2: ordered list to join
 */
List *merge(int(*compare)(void *, void *), List *l1, List *l2);

#endif