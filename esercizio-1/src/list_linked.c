#include "list.h"

#include <stddef.h>
#include "list.h"
#include <stdlib.h>


typedef struct _Node Node;
struct _Node {
  void *elem;
  Node *next;
  Node *prev;
};

struct _List {
  Node *top;
  Node *rear;
  int size;
};

struct _Iterator {
  List *list;
  Node *curr;
};


// List functions

List *List_create() {
  List *l = (List *)malloc(sizeof(List));
  l->size = 0; 
  l->top = NULL;
  l->rear = NULL;    
  return l;
}

void List_destroy(List *l) {
  if(l) {
    Node *p = l->top;
    while(p) {
      Node *tmp = p;
      p = p->next;
      free(tmp);
    }
    free(l);
  }
}

int List_empty(List *l) {
  return l && l->size==0;
}

int List_size(List *l) {
  if(l)
    return l->size;
  else
    return -1;
}

void List_add(List *l, void *elem) {
  if(l && elem) {
  	if(l->size==0) {
  		l->top =(Node *)malloc(sizeof(Node));
      l->top->elem = elem;
      l->top->next = NULL;
      l->top->prev = NULL;
  		l->rear = l->top;
  	} else {
  		Node *tmp =(Node *)malloc(sizeof(Node));
  		tmp->elem = elem;
      tmp->next = NULL;
      tmp->prev = l->rear;
      l->rear->next = tmp;
  		l->rear = tmp;
  	}
    l->size++;
  }
}

void List_add_index(List *l, void *elem, int index) {
	if(l && elem && index<l->size && index>=0) {
		if(index==0) {
      Node *tmp = (Node *)malloc(sizeof(Node));
      tmp->elem = elem;
      tmp->next = l->top;
      tmp->prev = NULL;
    } else {
      Node *p = l->top;
      int i;
      for(i=0; i<index; i++)
        p = p->next;
      p = p->prev;
      Node *tmp = (Node *)malloc(sizeof(Node));
      tmp->elem = elem;
      tmp->next = p->next;
      tmp->prev = p;
      p->next = tmp;
    }
		l->size++;
	}
}

void List_del(List *l) {
  if(l) {
    l->rear = l->rear->prev;
    l->rear->next = NULL;
    l->size--;
  }
}

void List_del_index(List *l, int index) {
  if(l && index<l->size && index>=0) {
    if(index==0) {
      Node *tmp = l->top;
      l->top = l->top->next;
      l->top->prev = NULL;
      free(tmp);
    } else {
      int i;
      Node *p = l->top;
      for(i=0; i<index; i++)
        p = p->next;
      p->prev->next = p->next;
      p->next->prev = p->prev;
      free(p);
    }
    l->size--;
  }
}

void *List_get(List *l, int index) {
  if(l && index<l->size && index>=0) {
    Node *p = l->top;
    int i;
    for(i=0; i<index; i++)
      p = p->next;
    return p->elem;
  } else
    return NULL;
}


// Iterator functions

Iterator *Iterator_create(List *l) {
  if(l) {
    Iterator *it = (Iterator *)malloc(sizeof(Iterator));
    it->list = l;
    it->curr = l->top;
    return it;
  } else
    return NULL;
}

void Iterator_destroy(Iterator *it) {
  if(it)
    free(it);
}

int Iterator_valid(Iterator *it) {
  return it && it->curr;
}

void *Iterator_get(Iterator *it) {
  if(it && Iterator_valid(it))
    return it->curr->elem;
  else
    return NULL;
}

void Iterator_next(Iterator *it){
  if(it && Iterator_valid(it))
    it->curr = it->curr->next;
}