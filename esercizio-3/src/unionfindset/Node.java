package unionfindset;

public class Node<T> {

  private T key;
  private int rank;
  private Node<T> parent;

  private Node(T key) {
    this.key = key;
    this.rank = 0; 
    this.parent = this;
  }

  public T key() { return key; }

  public int rank() { return rank; }

  public Node<T> parent() { return parent; }

  public Node<T> setParent(Node<T> parent) { this.parent=parent; }

  public void rankUp() { this.rank++; }
}