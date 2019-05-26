package graph;

import java.lang.Number;

public class Node<T,E extends Number> {
	
	private T key;
  private HashMap<T,<Link<T,E>> adjNodes;

  private class Link<T,E extends Number> {
    public Node<T> end;
    public E weight;

    public Link(Node<T> end, E weight) {
      this.end = end;
      this.weight = weight;
    } // Link
  } // private class


	public Node(T key) {
		this.key = key;
	}

  public T key() {
    return this.key;
  }

  public void addAdj(Node<T,E> x, Link<T,E> l) {
    adjNodes.put(x.key(),l);
  }

  public void delAdj(Node<T,E> x) {
    
  }

  public List<T> adjNodes() {
    List<T> tmp = new ArrayList<>(adjNodes.size());
    for(Link<T,E> l: adjNodes)
      tmp.add(l.end());
    return tmp;
  }

  public boolean hasAdj(T x) {
    return adjNodes.contains(x);
  }
}


