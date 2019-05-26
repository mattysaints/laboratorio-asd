package graph;

import java.lang.Number;

public class Arch<T,E extends Number & Comparable<E>> implements Comparable<Arch<T,E>>{

  private T start;
	private T end;
  private E weight;


	public Arch(T start, T end, E weight) {
		this.start = start;
    this.end = end;
    this.weight = weight;
	}

  public T start() {
    return this.start;
  }

  public T end() {
    return this.end;
  }

  public E weight() {
    return this.weight;
  }

  @Override
  public int compareTo(Arch<T,E> arch) {
    return this.weight.compareTo(arch.weight); 
  }

}