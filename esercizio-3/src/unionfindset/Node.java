package unionfindset;

public class Node<T> {

  private T elem;
  private Node<T> sup;

  private Node(T elem, Node<T> sup) {
    this.elem = elem;
    this.sup = sup;
  }

  public T getElem() { return elem; }

  public Node<T> getSup() { return sup; }

  public Node<T> setSup(Node<T> sup) { this.sup=sup; }
}