package graph;

public class Link<T> {
	private Node<T> start;
	private Node<T> end;

	public Link(Node<T> start, Node<T> end) {
		this.start = start;
		this.end = end;
	}

	public boolean equals(Link<T> l) {
		return this.start.equals(l.start) && this.end.equals(l.end);
	}


}