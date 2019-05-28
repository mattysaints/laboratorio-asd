package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphTests {

  private Graph<Integer,Integer> grNotOr;

  @Before
  public void createGraph() {
    grNotOr = new Graph<>(false);
  }

  @Test(expected=GraphException.class)
  public void test_add_null() throws Exception {
    grNotOr.add(null);
  }

  @Test
  public void test_add() throws Exception {
    assertTrue(grNotOr.add(3));
  }

  @Test
  public void test_add_alreadyIn() throws Exception {
    grNotOr.add(3);
    assertFalse(grNotOr.add(3));
  }

}