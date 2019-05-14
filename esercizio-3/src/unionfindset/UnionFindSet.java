package unionfindset;

public class UnionFindSet<T> {

  private Node<T>[] forest;

  /**
   * Constructor: creates an empty set
   */
  public UnionFindSet() {
    forest=null;
  } // UnionFindSet

  /**
   * Creates a discrete partition of the set
   * @param set: array of generuc type T representig the set
   */
  public void makeSet(T[] set) throws IllegalArgumentException, UnionFindSetException {
    if(forest!=null)
      throw new UnionFindSetException("This set already exists");
    else if(set==null || set.length<=0)
      throw new IllegalArgumentException("The pararameter type T[] (array) must be not null and not empty");
    else {
      forest = new Node<>[set.length];
      for(int i=0; i<set.length; i++)
        forest[i] = new Node<>(set[i]);
    }
  } // makeSet

  /**
   * Returns the node with the giver key
   * @param x: key of the node to find
   */
  private Node<T> findNode(T x) throws UnionFindSetException {
    for(Node<T> node: forest)
      if(node.key() == x)
        return node;
    throw new UnionFindSetException("Element not found in this set");
  } // findNode

  /**
   * Returns the leader of the set
   * @param x: element part of the set
   */
  public T find(T x) throws IllegalArgumentException, UnionFindSetException {
    if(x==null)
      throw new IllegalArgumentException("The pararameter type T must be not null");
    else {
      Node<T> node = findNode(x);
      if(node.parent()!=node)
        node.setParent(find(node.parent()));
      return node.key();
    }
  } // find

  /**
   * Makes the union of the two sets 
   * @param x: element part of the first set
   * @param x: element part of the second set
   */
  public void union(T x, T y) throws IllegalArgumentException, UnionFindSetException {
    if(x==null || y==null)
      throw new IllegalArgumentException("The pararameters type T must be not null");
    else {
      Node<T> xNode = findNode(x);
      Node<T> yNode = findNode(y);
      if(xNode.rank()>yNode.rank())
        yNode.setParent(xNode);
      else if(xNode.rank()==yNode.rank()) {
        xNode.setParent(yNode);
        yNode.rankUp();
      } else // xNode.rank() < yNode.rank()
        xNode.setParent(yNode);
    }
  } // union

} // class