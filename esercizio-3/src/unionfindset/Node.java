package unionfindset;

public class Node<T> {

  private T key;
  private int rank;
  private Node<T> parent;

  /**
   * Constructor: creates a new node
   * @param key: value to set to the key
   */
  private Node(T key) {
    this.key = key;
    this.rank = 0; 
    this.parent = this;
  } // Node

  /**
   * Returns the key of the node
   */
  public T key() {
    return key;
  } // key

  /**
   * Returns the rank of the node
   */
  public int rank() {
    return rank;
  } // rank

  /**
   * Returns the parent of the node
   */
  public Node<T> parent() {
    return parent; 
  } // parent

  /**
   * Sets the parent of the node
   * @param parent: node of the parent
   */
  public void setParent(Node<T> parent) {
    this.parent=parent;
  } // setParent

  /**
   * Increments the rank of the node
   */
  public void rankUp() {
    this.rank++;
  } // rankUp

} // class