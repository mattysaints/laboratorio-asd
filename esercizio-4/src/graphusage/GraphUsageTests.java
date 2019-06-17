package graphusage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import graph.Graph;
import graphusage.GraphUsage;
import graph.GraphException;
import graph.Arch;

public class GraphUsageTests {

  private Graph<String,Double> grOr;
  private Graph<String,Double> grNotOr;

	@Before
  public void createGraph() throws Exception {
    grOr = new Graph<>(true);

    grNotOr = new Graph<>(false);
    grNotOr.add("A");
    grNotOr.add("B");
    grNotOr.add("C");
    grNotOr.add("D");

    grNotOr.link("A","B",1.0);
    grNotOr.link("B","C",2.0);
    grNotOr.link("A","C",3.0);
    grNotOr.link("B","D",3.0);
    grNotOr.link("C","D",4.0);
    grNotOr.link("A","D",5.0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void test_mstKruskal_null() throws Exception {
    GraphUsage.mstKruskal(null);
  }

  @Test(expected=RuntimeException.class)
  public void test_mstKruskal_or() throws Exception {
    GraphUsage.mstKruskal(grOr);
  }

  @Test
  public void test_mstKruskal_notOr() throws Exception {
    assertEquals(6.0,GraphUsage.totDistance(GraphUsage.mstKruskal(grNotOr)),0);
  }

  @Test(expected=IllegalArgumentException.class)
  public void test_totDistance_null() throws Exception  {
    GraphUsage.totDistance(null);
  }

  @Test
  public void test_totDistance() throws Exception {
  	assertEquals(18.0,GraphUsage.totDistance(grNotOr),0);
  }

}