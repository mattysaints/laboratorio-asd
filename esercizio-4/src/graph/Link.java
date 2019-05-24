package graph;

import java.lang.Number;

public class Link<T,E extends Number> {

	private Node<T> end;
  private E weight;

	public Link(Node<T> end, E weight) {
		this.end = end;
    this.weight = weight;
	}

  public Node<T> end() {
    return this.end;
  }

  public E weight() {
    return this.weight;
  }

}