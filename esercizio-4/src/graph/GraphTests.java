package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphTests {

  private Graph<Integer,Integer> grNotOr;
  private Graph<Integer,Integer> grIsOr;

  @Before
  public void createGraph() {
    grNotOr = new Graph<>(false);
    grIsOr = new Graph<>(true);
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

  @Test 
  public void test_isOriented() {
  	assertFalse(grNotOr.isOriented());
  }

  /*@Test 
  public void test_isOriented_1() {
  	assertTrue(grIsOr.isOriented());
  }*/

  @Test
  public void test_link() throws Exception {
  	grNotOr.add(3);
  	grNotOr.add(5);
  	grNotOr.link(3,5,10);
  	assertTrue(grNotOr.containsLink(3,5));
  }

  /*@Test
  public void test_link_1() throws Exception {
  	grIsOr.add(3);
  	grIsOr.add(5);
  	grIsOr.link(3,5,10);
  	assertFalse(grIsOr.containsLink(5,3));
  }*/

  @Test
  public void test_containsNode() throws Exception {
  	grNotOr.add(6);
  	assertTrue(grNotOr.containsNode(6));
  }

  @Test
  public void test_del() throws Exception {
  	grNotOr.add(5);
  	grNotOr.del(5);
  	assertFalse(grNotOr.containsNode(5));
  }

  @Test
  public void test_del1() throws Exception {
  	grNotOr.add(5);
  	grNotOr.add(7);
  	grNotOr.link(5,7,10);
  	grNotOr.del(5);
  	assertFalse(grNotOr.containsNode(5));
  	assertTrue(grNotOr.containsNode(7));
  }

  @Test
  public void test_unlink() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	grNotOr.link(1,2,6);
  	grNotOr.unlink(1,2);
  	assertFalse(grNotOr.containsLink(1,2));
  }

  @Test
  public void test_size() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	assertTrue(2 == grNotOr.size());
  }

  @Test
  public void test_numLinks() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	grNotOr.add(4);
  	grNotOr.link(1,2,6);
  	grNotOr.link(2,4,5);
  	assertTrue(2 == grNotOr.numLinks());
  }

  @Test
  public void test_weight() throws Exception {
  	grNotOr.add(4);
  	grNotOr.add(5);
  	grNotOr.link(4,5,7);
  	assertTrue(7 == grNotOr.weight(4,5));
  }



}





