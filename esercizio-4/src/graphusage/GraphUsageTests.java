package graphusage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import graph.Graph;
import graphusage.GraphUsage;
import graph.GraphException;
import graph.Arch;

public class GraphUsageTests {

  private Graph<String,Double> grNotOr;

	@Before
  public void createGraph() throws Exception {
    grNotOr = new Graph<>(false);
    grNotOr.add("A"); //A
    grNotOr.add("B"); //B
    grNotOr.add("C"); //C
    grNotOr.add("D"); //D

    grNotOr.link("A","B",1.0);
    grNotOr.link("B","C",2.0);
    grNotOr.link("A","C",3.0);
    grNotOr.link("A","D",5.0);
    grNotOr.link("B","D",3.0);
    grNotOr.link("B","C",4.0);
  }

  @Test
  public void test_totDistance() {
  	assertEquals(6.0,GraphUsage.totDistance(grNotOr));
  }






}