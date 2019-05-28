package graphusage;

import java.util.Comparator;
import graph.Arch;

public class ArchComparator<T> implements Comparator<Arch<String,Double>> {

  @Override
  public int compare(Arch<String,Double> a1, Arch<String,Double> a2) {
    return a1.weight().compareTo(a2.weight());
  }

}