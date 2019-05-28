package graph;

import java.lang.Number;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Graph<T,E extends Number & Comparable<E>> {

	private HashMap<T,Node<T,E>> nodes;
	private boolean isOriented;


	public Graph(boolean iOriented) {
		this.nodes  = new HashMap<>();
		this.isOriented = isOriented;
	}

	public boolean add(T x) throws GraphException{
		if(x==null)
      throw new GraphException("add: the parameter cannot be null");
    else if(nodes.containsKey(x))
      return false;
    else {
      nodes.put(x,new Node<>(x));
      return true;
    }
	}

	public boolean link(T x, T y, E weight) throws GraphException {
		if(x==null || y==null || weight==null)
			throw new GraphException("link: the parameters cannot be null");
		if(!nodes.containsKey(x) || !nodes.containsKey(y) ||
       nodes.get(x).hasAdj(y) || nodes.get(y).hasAdj(x))
      return false;
		else {
      if(!isOriented)
        nodes.get(y).addAdj(nodes.get(x),weight);
			nodes.get(x).addAdj(nodes.get(y),weight);
      return true;
		}
	}

	public boolean isOriented() {
		return isOriented;
	}

	public boolean containsNode(T x) {
		if(x==null)
			return false;
		else
			return nodes.containsKey(x);
	}

	public boolean containsLink(T x, T y) {
		if(x==null || y==null)
			return false;
		else
			return nodes.containsKey(x) && nodes.get(x).hasAdj(y);
	}

	public boolean del(T x) throws GraphException {
    if(x==null)
      throw new GraphException("del: the parameter cannot be null");
    else if(!nodes.containsKey(x))
      return false;
    else {
      for(Node<T,E> n: nodes.values())
        if(nodes.get(x)!=n && n.hasAdj(x))
          n.delAdj(nodes.get(x));
  		nodes.remove(x);
      return true;
	  }
  }

	public boolean unlink(T x, T y) throws GraphException {
		if(x==null || y==null)
			throw new GraphException("unlink: the parameters cannot be null");
		else if(!this.containsLink(x,y))
      return false;
    else {
      if(!isOriented)
        nodes.get(y).delAdj(nodes.get(x));
      nodes.get(x).delAdj(nodes.get(y));
      return true;
		}
	}

	public int size() {
		return nodes.size();
	}

	public int numLinks() {
		int res = 0;
    for(Node<T,E> n: nodes.values())
      res += n.links();
    return res;
	}

  public List<T> nodeList() {
    return new ArrayList<>(nodes.keySet());
  }

  public List<Arch<T,E>> archList() {
    HashSet<Arch<T,E>> set = new HashSet<>(this.numLinks());
    for(Node<T,E> n: nodes.values())
      set.addAll(n.archList());
    return new ArrayList<>(set);
  }

  public List<T> adjList(T x) throws GraphException {
    if(x == null)
      throw new GraphException("adjList: parameter cannot be null");
    else
      return nodes.get(x).adjNodes();
  }

  public E weight(T x, T y) throws GraphException {
    if(x==null || y==null)
      throw new GraphException("weight: arch of null values");
    else if(!nodes.containsKey(x) || !nodes.get(x).hasAdj(y))
      throw new GraphException("weight: arch not found");
    else
      return nodes.get(x).weight(y);
  }

}