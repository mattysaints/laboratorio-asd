package graphusage;

import graph.Graph;
import graph.Arch;
import unionfindset.UnionFindSet;
import unionfindset.UnionFindSetException;

public class GraphUsage {

  public static Graph<String,Double> mstKruskal(Graph<String,Double> graph, String source) throws UnionFindSetException {
    if(graph==null || source==null)
      throw new IllegalArgumentException("mstKruskal: parameters cannot be null");
    else if(graph.isOriented())
      throw new RuntimeException("mstKruskal: the graph must be not oriented");
    else {
      Graph<String,Double> mst = new Graph<>(false);
      UnionFindSet<String> part = new UnionFindSet<>();
      part.makeSet(graph.nodeList().toArray(new String[0]));
      return mst;
    }
  }

}