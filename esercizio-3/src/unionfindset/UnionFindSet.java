package unionfindset;

public class UnionFindSet<T> {

  private Object[] array;

  /**
   * Constructor: creates an empty set
   */
  public UnionFindSet() {
    array=null;
  } // UnionFindSet

  /**
   * Creates a discrete partition of the set
   * @param set: array of generuc type T representig the set
   */
  public void makeSet(T[] set) throws IllegalArgumentException, UnionFindSetException {
    if(array!=null)
      throw new UnionFindSetException("This set already exists");
    else if(set==null || set.length<=0)
      throw new IllegalArgumentException("The pararameter type T[] (array) must be not null and not empty");
    else {
      array = new Object[set.length];
      for(int i=0; i<set.length; i++)
        array[i] = new Node<>(set[i]);
    }
  } // makeSet

  /**
   * Returns the node with the giver key
   * @param x: key of the node to find
   */
  private Node<T> node(T x) throws UnionFindSetException {
    for(Object obj: array) {
      
      @SuppressWarnings("unchecked")
      Node<T> node = (Node<T>)obj;
      if(node.key() == x)
        return node;
    }
    throw new UnionFindSetException("Element not found in this set");
  } // findNode

  /**
   * Returns the node of the set leader
   * @param x: node of the element part of the set
   */
  private Node<T> findNode(Node<T> node) {
    if(node.parent()!=node)
      node.setParent(findNode(node.parent()));
    return node;
  } // findNode

  /**
   * Returns the leader of the set
   * @param x: element part of the set
   */
  public T find(T x) throws IllegalArgumentException, UnionFindSetException {
    if(x == null)
      throw new IllegalArgumentException("The pararameter type T must be not null");
    else
      return findNode(node(x)).key();
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
      Node<T> xRoot = findNode(node(x));
      Node<T> yRoot = findNode(node(y));
      if(xRoot.rank()>yRoot.rank())
        yRoot.setParent(xRoot);
      else if(xRoot.rank()==yRoot.rank()) {
        xRoot.setParent(yRoot);
        yRoot.rankUp();
      } else
        xRoot.setParent(yRoot);
    }
  } // union

} // class