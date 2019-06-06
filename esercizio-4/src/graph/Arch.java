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

  @Override
  public boolean equals(Object obj) {
    if(this == obj)
      return true;
    else if(obj == null || !(obj instanceof Arch))
      return false;
    else {
      @SuppressWarnings("unchecked")
      Arch<T,E> arch = (Arch<T,E>)obj;
      return (this.start.equals(arch.start) || this.start==arch.start) &&
             (this.end.equals(arch.end) || this.end==arch.end)  &&
             (this.weight.equals(arch.weight) || this.weight==arch.weight);             
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}