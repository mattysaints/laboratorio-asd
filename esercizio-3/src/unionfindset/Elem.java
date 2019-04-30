public class Elem extends UnionFindSet<T> {
  
  private T elem;
  private int index;

  public Elem(T elem) {
    this.elem = elem;
    this.index = 0;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}