package graph;

import java.lang.Number;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// support class of Graph class
class Node<T,E extends Number> {
	
	private T key;
  private HashMap<T,Link<T,E>> adjNodes;

  // private class representing the arch of the graph
  private class Link<T,E extends Number> {
    public T end;
    public E weight;

    public Link(T end, E weight) {
      this.end = end;
      this.weight = weight;
    } // Link
  } // private class

  /**
   * Creates a new node
   * @param key: key of the node
   */
	public Node(T key) {
		this.key = key;
    adjNodes = new HashMap<>();
	} // Node

  /**
   * Returns the key of the node
   */
  public T key() {
    return this.key;
  } // key

  /**
   * Returns the weight of the arch
   * @param x: end of the arch
   */
  public E weight(T x) {
    return adjNodes.get(x).weight;
  } // weight

  /**
   * Adds one arch from the current node
   * @param x: end of the arch
   * @param weight: weight of the arch
   */
  public void addAdj(T x, E weight) {
    adjNodes.put(x,new Link<>(x,weight));
  } // addAdj

  /**
   * Deletes one arch from the current node
   * @param x: end of the arch
   */
  public void delAdj(T x) {
    adjNodes.remove(x);
  } // delAdj

  /**
   * Returns a list of the adjacent nodes
   */
  public List<T> adjList() {
    List<T> tmp = new ArrayList<>(adjNodes.size());
    for(Link<T,E> l: adjNodes.values())
      tmp.add(l.end);
    return tmp;
  } // adjList

  /**
   * Return true if the current node is adjacent to x
   * @param x: end of the arch
   */
  public boolean hasAdj(T x) {
    return adjNodes.containsKey(x);
  } // hasAdj

  /**
   * Returns the number of adjacents nodes
   */
  public int numLinks() {
    return adjNodes.size();
  } // numLinks

  /**
   * Returns the list of arches from the current node
   */
  public List<Arch<T,E>> archList() {
    ArrayList<Arch<T,E>> res = new ArrayList<>(adjNodes.size());
    for(Link<T,E> link: adjNodes.values())
      res.add(new Arch<>(this.key, link.end, link.weight));
    return res;
  } // archList

} // class