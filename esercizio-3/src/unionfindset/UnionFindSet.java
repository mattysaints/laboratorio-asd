package unionfindset;

import java.util.ArrayList;

public class UnionFindSet<T> {

  private ArrayList<Node<T>> forest;

  public UnionFindSet() { forest=null; }

  // idea di implementazione
  public void makeSet(T[] set) throws IllegalArgumentException {
    if(forest!=null)
      throw new UnionFindSetException("This set already exists");
    else if(set==null || set.length<=0)
      throw new IllegalArgumentException("The pararameter type T[] (array) must be not null and not empty");
    else {
      Node<T> sup = new Node<>(set[0],null);
      forest = new ArrayList<>(set.length);
      forest.add(sup);
      for(int i=1; i<set.length; i++)
        forest.add(new Node<>(set[i],sup));
    }
  }

  public void union(T x, T y) throws IllegalArgumentException, UnionFindSetException {
    if(x!=null && y!=null) {
      int hx = 0, hy = 0;
    } else
      throw new IllegalArgumentException("The pararameters type T must be not null");
  }

  public UnionFindSet<T> find(T x) {

  }
}