List *merge(int(*compare)(List *l1, List *l2)) {
	List *result = List_create(List_size(l1)+List_size(l2));
	Iterator *it1 = Iterator_create(l1);
	Iterator *it2 = Iterator_create(l2);

	while(Iterator_valid(it1) && Iterator_valid(it2)) {
		if(compare(Iterator_get(it1), Iterator_get(it2)) <= 0) {
			List_add(result, Iterator_get(it1));
			Iterator_next(it1);
		}	else {
			List_add(result, Iterator_get(it2));
			Iterator_next(it2);
		}
	}

	while(Iterator_valid(it1)) {
		List_add(result, Iterator_get(it1));
		Iterator_next(it1);
	}

	while(Iterator_valid(it2)) {
		List_add(result, Iterator_get(it2));
		Iterator_next(it2);
	}
}


int main() {
  
}

