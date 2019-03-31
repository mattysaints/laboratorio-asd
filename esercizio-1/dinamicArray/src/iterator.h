typedef struct _Iterator Iterator;

//Create a Iterator for a list
Iterator *Iterator_create(List *l);

//Destroy an Iterator 
void Iterator_destroy(Iterator *it);

//Check if the iterator is Valid or NULL
int Iterator_isValid(Iterator *it);

//Get elem in current index
void *Iterator_getElem;

//Move the position of iterator
void Iterator_getNext;