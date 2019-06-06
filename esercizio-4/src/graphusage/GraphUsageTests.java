package graphusage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import graph.Graph;
import graph.GraphException;
import graph.Arch;

public class GraphUsageTests {

  private Graph<Integer,Integer> grNotOr;

	@Before
  public void createGraph() throws Exception{
    grNotOr = new Graph<>(false);
    grNotOr.add(0); //A
    grNotOr.add(1); //B
    grNotOr.add(2); //C
    grNotOr.add(3); //D

    grNotOr.link(0,1,1);
    grNotOr.link(1,2,2);
    grNotOr.link(0,2,3);
    grNotOr.link(0,3,5);
    grNotOr.link(1,3,3);
    grNotOr.link(2,3,4);
  }






}