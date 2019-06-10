package graph;

import java.lang.Number;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Graph<T,E extends Number> {

	private HashMap<T,Node<T,E>> nodes;
	private boolean isOriented;

  /**
   * Creates a new graph
   * @param isOriented: if true the graph created will be oriented
   */
	public Graph(boolean isOriented) {
		this.nodes  = new HashMap<>();
		this.isOriented = isOriented;
	} // Graph

  /**
   * Returns true if one node is successfully added to the graph
   * @param x: key of the node
   */
	public boolean add(T x) throws GraphException{
		if(x==null)
      throw new GraphException("add: the parameter cannot be null");
    else if(nodes.containsKey(x))
      return false;
    else {
      nodes.put(x,new Node<>(x));
      return true;
    }
	} // add

  /**
   * Returns true if one arch is succesfully added in the graph
   * @param x: start of the arch
   * @param y: end of the arch
   * @param weight: weight of the arch
   */
	public boolean link(T x, T y, E weight) throws GraphException {
		if(x==null || y==null || weight==null)
			throw new GraphException("link: the parameters cannot be null");
		else if(!(nodes.containsKey(x) && nodes.containsKey(y)))
      return false;
    else if(!isOriented && nodes.get(y).hasAdj(x) || nodes.get(x).hasAdj(y))
      return false;
		else {
      if(!isOriented)
        nodes.get(y).addAdj(x,weight);
			nodes.get(x).addAdj(y,weight);
      return true;
		}
	} // link

  /**
   * Returns true if the graph is oriented
   */
	public boolean isOriented() {
		return isOriented;
	} // isOriented

  /**
   * Returns true if the node is in the graph
   * @param x: key of the node to find
   */
	public boolean containsNode(T x) {
		if(x==null)
			return false;
		else
			return nodes.containsKey(x);
	} // containsNode

  /**
   * Returns true if the arch is in the graph
   * @param x: start of the arch
   * @param y: end of the arch
   */
	public boolean containsLink(T x, T y) {
		if(x==null || y==null)
			return false;
		else
			return nodes.containsKey(x) && nodes.get(x).hasAdj(y);
	} // containsLink

  /**
   * Returns true if the node has been successfully deleted from the graph
   * @param x: node to delete
   */
	public boolean del(T x) throws GraphException {
    if(x==null)
      throw new GraphException("del: the parameter cannot be null");
    else if(!nodes.containsKey(x))
      return false;
    else {
      for(Node<T,E> n: nodes.values())
        if(nodes.get(x)!=n && n.hasAdj(x))
          n.delAdj(x);
  		nodes.remove(x);
      return true;
	  }
  } // del

  /**
   * Returns true if the arch has been successfully deleted from the graph
   * @param x: start of the arch
   * @param y: end of the arch
   */
	public boolean unlink(T x, T y) throws GraphException {
		if(x==null || y==null)
			throw new GraphException("unlink: the parameters cannot be null");
		else if(!this.containsLink(x,y))
      return false;
    else {
      if(!isOriented)
        nodes.get(y).delAdj(x);
      nodes.get(x).delAdj(y);
      return true;
		}
	} // unlink

  /**
   * Returns the number of nodes in the graph
   */
	public int size() {
		return nodes.size();
	} // size

  /**
   * Returns the number of arches in the graph
   * (NOTE: if the graph is not oriented the number refers to the not oriented arches)
   */
	public int numLinks() {
		int res = 0;
    for(Node<T,E> n: nodes.values())
      res += n.numLinks();
    if(!isOriented)
      res /= 2;
    return res;
	} // numLinks

  /**
   * Returns the list of the nodes in the graph
   */
  public List<T> nodeList() {
    return new ArrayList<>(nodes.keySet());
  } // nodeList

  /**
   * Returns the list of the arches in the graph
   * (NOTE: if the graph is not oriented the list will contain not oriented arches)
   */
  public List<Arch<T,E>> archList() {
    HashSet<Arch<T,E>> set = new HashSet<>(this.numLinks());
    for(Node<T,E> n: nodes.values())
      for(Arch<T,E> ar: n.archList())
        if(isOriented || (!isOriented && !set.contains(ar.reverse())))
          set.add(ar);
    return new ArrayList<>(set);
  } // archList

  /**
   * Returns the list of the adjacent nodes
   * @param x: the node of which to find the adjacents
   */
  public List<T> adjList(T x) throws GraphException {
    if(x == null)
      throw new GraphException("adjList: parameter cannot be null");
    else
      return nodes.get(x).adjList();
  } // adjList

  /**
   * Returns the weight of the arch
   * @param x: start of the arch
   * @param y: end of the arch
   */
  public E weight(T x, T y) throws GraphException {
    if(x==null || y==null)
      throw new GraphException("weight: arch of null values");
    else if(!nodes.containsKey(x) || !nodes.get(x).hasAdj(y))
      throw new GraphException("weight: arch not found");
    else
      return nodes.get(x).weight(y);
  } // weight

} // class