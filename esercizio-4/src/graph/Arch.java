package graph;

import java.lang.Number;
import java.util.Objects;

// class representing the arch of a graph
public class Arch<T,E extends Number> {

  private T start;
	private T end;
  private E weight;

  /**
   * Creates a new arch
   * @param start: starting node
   * @param end: ending node
   * @param weight: weight of the arch
   */
	Arch(T start, T end, E weight) {
		this.start = start;
    this.end = end;
    this.weight = weight;
	} // Arch

  /**
   * Returns the reverse arch of this arch
   */
  Arch<T,E> reverse() {
    return new Arch<>(this.end,this.start,this.weight);
  } // reverse

  /**
   * Returns the starting node of the arch
   */
  public T start() {
    return this.start;
  } // start

  /**
   * Returns the ending node of the arch
   */
  public T end() {
    return this.end;
  } // end

  /**
   * Returns the weight of the arch
   */
  public E weight() {
    return this.weight;
  } // weight

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
  } // equals

  @Override
  public int hashCode() {
    return Objects.hash(start,end,weight);
  } // hashCode

  @Override
  public String toString() {
    return "("+start.toString()+", "+end.toString()+", "+weight.toString()+")";
  } // toString

} // class