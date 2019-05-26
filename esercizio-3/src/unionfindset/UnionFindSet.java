package unionfindset;

import java.util.HashMap;

public class UnionFindSet<T> {

  private HashMap<T,Node<T>> set;

  /**
   * Constructor: creates an empty set
   */
  public UnionFindSet() {
    this.set = null;
  } // UnionFindSet

  /**
   * Returns true is the discrete partition of the set has been successfully created
   * @param set: array of generic type T representing the set
   */
  public boolean makeSet(T[] set) throws UnionFindSetException {
    if(set==null || set.length<=0)
      throw new UnionFindSetException("makeSet: the pararameter (array) must be not null and not empty");
    else if(this.set!=null)
      return false;
    else {
      this.set = new HashMap<>(set.length);
      for(T elem: set)
        this.set.put(elem, new Node<>(elem));
      return true;
    }
  } // makeSet

  /**
   * Returns the node of the set leader
   * @param node: node of the element part of the set
   */
  private Node<T> findNode(Node<T> node) {
    if(node.parent()!=node)
      node.setParent(findNode(node.parent()));
    return node.parent();
  } // findNode

  /**
   * Returns the leader of the set
   * @param x: element part of the set
   */
  public T find(T x) throws UnionFindSetException {
    if(x == null)
      throw new UnionFindSetException("find: the pararameter must be not null");
    else if(!set.containsKey(x))
      throw new UnionFindSetException("find: element not found in the set");
    else
      return findNode(set.get(x)).key();
  } // find

  /**
   * Makes the union of the two sets 
   * @param x: element part of the first set
   * @param y: element part of the second set
   */
  public boolean union(T x, T y) throws UnionFindSetException {
    if(x==null || y==null)
      throw new UnionFindSetException("find: the pararameters must be not null");
    else if(!set.containsKey(x) || !set.containsKey(y))
      return false;
    else {
      Node<T> xRoot = findNode(set.get(x));
      Node<T> yRoot = findNode(set.get(y));
      if(xRoot.rank()>yRoot.rank())
        yRoot.setParent(xRoot);
      else if(xRoot.rank()==yRoot.rank()) {
        yRoot.setParent(xRoot);
        xRoot.rankUp();
      } else
        xRoot.setParent(yRoot);
      return true;
    }
  } // union

} // class