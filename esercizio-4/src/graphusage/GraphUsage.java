package graphusage;

import graph.Graph;
import graph.GraphException;
import graph.Arch;
import unionfindset.UnionFindSet;
import unionfindset.UnionFindSetException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphUsage {

  /**
   * Returns the minimum spanning tree of the graph
   * @param graph: the mst is calculated from the graph parameter
   */
  public static Graph<String,Double> mstKruskal(Graph<String,Double> graph) throws Exception {
    if(graph==null)
      throw new IllegalArgumentException("mstKruskal: parameters cannot be null");
    else if(graph.isOriented())
      throw new RuntimeException("mstKruskal: the graph must be not oriented");
    else {
      Graph<String,Double> mst = new Graph<>(false);
      UnionFindSet<String> part = new UnionFindSet<>();
      part.makeSet(graph.nodeList().toArray(new String[0]));
      List<Arch<String,Double>> archList = graph.archList();
      archList.sort(new ArchComparator_StrDbl());

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

  /**
   * Returns the not oriented-weighted-graph loaded from a file
   * @param filepath: path of the file to open
   */
  public static Graph<String,Double> loadGraph(String filepath) throws IOException, GraphException {
    Graph<String,Double> gr = new Graph<>(false);
    BufferedReader br = new BufferedReader(new FileReader(filepath));
    String line;
    
    while((line=br.readLine())!=null) {
      String[] tmp = line.split(",");
      gr.add(tmp[0]);
      gr.add(tmp[1]);
      gr.link(tmp[0],tmp[1],Double.parseDouble(tmp[2]));
    }
    br.close();
    return gr;
  } // loadGraph

  /**
   * Returns the total weight of the arches in the graph
   * @param gr: the weight is calculated from the graph parameter
   */
  public static double totDistance(Graph<String,Double> gr) throws IllegalArgumentException {
    if(gr==null)
      throw new IllegalArgumentException("totDistance: argument cannot be null");
    List<Arch<String,Double>> archList = gr.archList();
    int res = 0;
    for(Arch<String,Double> ar: archList)
      res += ar.weight();
    return res;
  }

  /**
   * @param args: command line arguments: should only contain one argument corresponding to the filepath
   */
  public static void main(String[] args) throws GraphException, IOException, Exception {
    if(args.length < 1)
      throw new Exception("Usage: GraphUsage <file_name>");
    long beginTime = System.nanoTime();
    Graph<String,Double> gr, mst;
    
    gr = loadGraph(args[0]);
    mst = mstKruskal(gr);
    System.out.println("MST Kruskal data ("+((System.nanoTime()-beginTime)/1000000.0)+" ms)\n"+
                     mst.size()+ " nodes\n"+
                     mst.numLinks()+" arches\n"+
                     (totDistance(mst)/1000.0)+" (km) total weight");
  } // main

} // class