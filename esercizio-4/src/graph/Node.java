package graph;

import java.lang.Number;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Node<T,E extends Number> {
	
	private T key;
  private HashMap<T,Link<T,E>> adjNodes;

  private class Link<T,E extends Number> {
    public T end;
    public E weight;

    public Link(T end, E weight) {
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

  public void addAdj(T x, E weight) {
    adjNodes.put(x,new Link<>(x,weight));
  }

  public void delAdj(T x) {
    adjNodes.remove(x);
  }

  public List<T> adjList() {
    List<T> tmp = new ArrayList<>(adjNodes.size());
    for(Link<T,E> l: adjNodes.values())
      tmp.add(l.end);
    return tmp;
  }

  public boolean hasAdj(T x) {
    return adjNodes.containsKey(x);
  }

  public int numLinks() {
    return adjNodes.size();
  }

  public List<Arch<T,E>> archList() {
    ArrayList<Arch<T,E>> res = new ArrayList<>(adjNodes.size());
    for(Link<T,E> link: adjNodes.values())
      res.add(new Arch<>(this.key, link.end, link.weight));
    return res;
  }

}