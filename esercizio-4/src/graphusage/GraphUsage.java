package graphusage;

import graph.Graph;
import graph.GraphException;
import graph.Arch;
import unionfindset.UnionFindSet;
import unionfindset.UnionFindSetException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class GraphUsage {

  public static Graph<String,Double> mstKruskal(Graph<String,Double> graph, String source) throws Exception {
    if(graph==null || source==null)
      throw new IllegalArgumentException("mstKruskal: parameters cannot be null");
    else if(graph.isOriented())
      throw new RuntimeException("mstKruskal: the graph must be not oriented");
    else {
      Graph<String,Double> mst = new Graph<>(false);
      UnionFindSet<String> part = new UnionFindSet<>();
      part.makeSet(graph.nodeList().toArray(new String[0]));
      List<Arch<String,Double>> archList = graph.archList();
      archList.sort(new ArchComparator<>());

      for(Arch<String,Double> ar: archList) {
        String u = ar.start(), w = ar.end();
        if(part.find(u) != part.find(w)) {
          mst.add(u);
          mst.add(w);
          mst.link(u,w,ar.weight());
          part.union(u,w);
        }
      }
      return mst;
    }
  } // mstKruskal

  public static Graph<String,Double> loadGraph(String filepath) throws Exception {
    Graph<String,Double> gr = new Graph<>(false);
    BufferedReader br = new BufferedReader(new FileReader(filepath));

    String line;
    while((line=br.readLine())!=null) {
      String[] tmp = line.split(",");

      // controllo dei nodi con nomi simili

      gr.add(tmp[0]);
      gr.add(tmp[1]);
      gr.link(tmp[0],tmp[1],Double.parseDouble(tmp[2]));
    }
    br.close();
    return gr;
  }

  public static double totDistance(Graph<String,Double> gr) {
    List<Arch<String,Double>> archList = gr.archList();
    int res = 0;
    for(Arch<String,Double> ar: archList)
      res += ar.weight();
    return res;
  } 

}