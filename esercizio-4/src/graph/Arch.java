package graph;

import java.lang.Number;

public class Arch<T,E extends Number> {

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

  public boolean equals(Arch<T,E> arch) {
    return this.weight.equals(arch.weight) &&
          (this.start.equals(arch.start) && this.end.equals(arch.end) ||
           this.start.equals(arch.end) && this.end.equals(arch.start));
  }
}