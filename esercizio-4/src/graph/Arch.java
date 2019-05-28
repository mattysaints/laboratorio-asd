package graph;

import java.lang.Number;
import java.util.Objects;

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

}