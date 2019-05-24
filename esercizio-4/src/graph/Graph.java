package graph;

import java.lang.Number;
import java.util.HashMap;

public class Graph<T,E extends Number> {

	private HashMap<T,Node<T>> nodes;
	private boolean isOriented;


	public Graph(boolean iOriented) {
		this.nodes  = new HashMap<>();
		this.isOriented = isOriented
	}

	public boolean add(T x) throws GraphException{
		if(x==null)
      throw new GraphException("add: the parameter cannot be null");
    else if(nodes.contains(x))
      return false;
    else {
      nodes.put(x,new Node<>(x));
      return true;
    }
	}

	public boolean link(T x, T y, E weight) throws GraphException {
		if(x==null || y==null || weight==null)
			throw new GraphException("link: the parameters cannot be null");
		if(!nodes.contains(x) || !nodes.contains(y))
      return false;
		else {
      // aggiungere se il grafo non è orientato
			Node<T,E> tmp = nodes.get(y);
			nodes.get(x).addAdj(tmp,new Link(tmp,weight));
      return true;
		}
	}

	public boolean isOriented() {
		return oriented;
	}

	public boolean containsNode(T x) {
		if(x==null)
			return false;
		else
			return nodes.contains(x);
	}

	public boolean containsLink(T x, T y) {
		if(x==null || y==null)
			return false;
		else
			return nodes.contains(x) && nodes.get(x).hasAdj(y);
	}

	public boolean del(T x) throws GraphException {
    if(x==null)
      throw new GraphException("del: the parameter cannot be null");
    else if(!nodes.contains(x))
      return false;
    else {
  		//eliminare tutti gli archi
  		return nodes.remove(x);
	  }
  }

	public boolean unlink(T x, T y) {
		if(x==null || y==null)
			throw new GraphException("unlink: the parameters cannot be null");
		else if(!this.containsLink(x,y))
      return false;
    else {
      // distinguere arco diretto
			// elimina arco
      return true;
		}
	}

	public int size() {
		return nodes.size();
	}

	public int links() {
		return links.size();
	}

}