package graph;

import java.lang.Number;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Node<T,E extends Number & Comparable<E>> {
	
	private T key;
  private HashMap<T,Link<T,E>> adjNodes;

  private class Link<T,E extends Number & Comparable<E>> {
    public Node<T,E> end;
    public E weight;

    public Link(Node<T,E> end, E weight) {
      this.end = end;
      this.weight = weight;
    } // Link
  } // private class


	public Node(T key) {
		this.key = key;
    adjNodes = new HashMap<>();
	}

  public T key() {
    return this.key;
  }

  public E weight(T x) {
    return adjNodes.get(x).weight;
  }

  public void addAdj(Node<T,E> x, E weight) {
    adjNodes.put(x.key,new Link<>(x,weight));
  }

  public void delAdj(Node<T,E> x) {
    adjNodes.remove(x.key);
  }

  public List<T> adjNodes() {
    List<T> tmp = new ArrayList<>(adjNodes.size());
    for(Link<T,E> l: adjNodes.values())
      tmp.add(l.end.key);
    return tmp;
  }

  public boolean hasAdj(T x) {
    return adjNodes.containsKey(x);
  }

  public int links() {
    return adjNodes.size();
  }

  public List<Arch<T,E>> archList() {
    ArrayList<Arch<T,E>> res = new ArrayList<>(adjNodes.size());
    for(Link<T,E> link: adjNodes.values())
      res.add(new Arch<>(this.key, link.end.key, link.weight));
    return res;
  }

}