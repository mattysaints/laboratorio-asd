public abstract class UnionFindSet<T> {

  public static UnionFindSet makeSet(T[] array) {
    UnionFindSet<T> res = new Cons(array);
  }
}