package unionfindset;

import java.util.ArrayList;

public class UnionFindSet<T> {

  private Node<T>[] forest;

  public UnionFindSet() { forest=null; }

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
  }

  public void union(T x, T y) throws IllegalArgumentException, UnionFindSetException {
    if(x!=null && y!=null) {
      Node<T> xNode = findNode(x);
      Node<T> yNode = findNode(y);
      if(xNode.rank()>yNode.rank())
        yNode.setParent(xNode);
      else if(xNode.rank()==yNode.rank()) {
        xNode.setParent(yNode);
        xNode.rankUp();
      } else // xNode.rank() < yNode.rank()
        xNode.setParent(yNode);
    } else
      throw new IllegalArgumentException("The pararameters type T must be not null");
  }

  private Node<T> findNode(T x) throws UnionFindSetException {
    for(Node<T> node: forest)
      if(node.equals(x))
        return node;
    throw new UnionFindSetException("Element not found in this set");
  }

  public Node<T> find(T x) throws IllegalArgumentException, UnionFindSetException {
    if(x==null)
      throw new IllegalArgumentException("The pararameter type T must be not null");
    else {
      Node<T> node = findNode(x);
      if(node.parent()!=node)
        node.setParent(find(node.parent()));
      return node;
    }
  }
}