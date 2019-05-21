package graph;

public class Node<T> {
	
	private T key;

	public Node(T key) {
		this.key = key;
	}

	public boolean equals(Node<T> n) {
		return this.key.equals(n.key);
	}

}


