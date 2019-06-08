package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

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
    assertTrue(grNotOr.containsNode(3));
  }

  @Test
  public void test_add_alreadyIn() throws Exception {
    grNotOr.add(3);
    assertFalse(grNotOr.add(3));
  }

  @Test 
  public void test_isOriented() {
  	assertFalse(grNotOr.isOriented());
    assertTrue(grIsOr.isOriented());
  }

  @Test
  public void test_link_notOr() throws Exception {
  	grNotOr.add(3);
  	grNotOr.add(5);
  	grNotOr.link(3,5,10);
  	assertTrue(grNotOr.containsLink(3,5));
    assertTrue(grNotOr.containsLink(5,3));
  }

  @Test
  public void test_link_isOr() throws Exception {
  	grIsOr.add(3);
  	grIsOr.add(5);
  	grIsOr.link(3,5,10);
    assertTrue(grIsOr.containsLink(3,5));
  	assertFalse(grIsOr.containsLink(5,3));
  }

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
  public void test_del_linked_isOr() throws Exception {
    grIsOr.add(5);
    grIsOr.add(7);
    grIsOr.link(5,7,10);
    grIsOr.del(5);
    assertFalse(grIsOr.containsNode(5));
    assertFalse(grIsOr.containsLink(5,7));
  }

  @Test
  public void test_del_linked_notOr() throws Exception {
  	grNotOr.add(5);
  	grNotOr.add(7);
  	grNotOr.link(5,7,10);
  	grNotOr.del(5);
  	assertFalse(grNotOr.containsNode(5));
  	assertFalse(grNotOr.containsLink(5,7));
    assertFalse(grNotOr.containsLink(7,5));
  }

  @Test
  public void test_unlink_isOr() throws Exception {
    grIsOr.add(1);
    grIsOr.add(2);
    grIsOr.link(1,2,6);
    grIsOr.unlink(1,2);
    assertFalse(grIsOr.containsLink(1,2));
  }


  @Test
  public void test_unlink_notOr() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	grNotOr.link(1,2,6);
  	grNotOr.unlink(1,2);
  	assertFalse(grNotOr.containsLink(1,2));
    assertFalse(grNotOr.containsLink(2,1));
  }

  @Test
  public void test_size() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	assertEquals(2,grNotOr.size());
  }

  @Test
  public void test_numLinks_isOr() throws Exception {
    grIsOr.add(1);
    grIsOr.add(2);
    grIsOr.add(4);
    grIsOr.link(1,2,6);
    grIsOr.link(2,1,6);
    grIsOr.link(2,4,5);
    assertEquals(3,grIsOr.numLinks());
  }

  @Test
  public void test_numLinks_notOr() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	grNotOr.add(4);
  	grNotOr.link(1,2,6);
    grNotOr.link(2,1,6);
  	grNotOr.link(2,4,5);
  	assertEquals(2,grNotOr.numLinks());
  }

  @Test
  public void test_weight() throws Exception {
  	grNotOr.add(4);
  	grNotOr.add(5);
  	grNotOr.link(4,5,7);
  	assertEquals((Integer)7,grNotOr.weight(4,5));
  }

  @Test
  public void test_archList_isOr() throws Exception {
    grIsOr.add(1);
    grIsOr.add(2);
    grIsOr.add(4);
    grIsOr.link(1,2,6);

    List<Arch<Integer,Integer>> list = new ArrayList<>(3);
    list.add(new Arch<>(1,2,6));
    assertEquals(list,grIsOr.archList());
  }

  @Test
  public void test_archList_notOr() throws Exception {
    grNotOr.add(1);
    grNotOr.add(2);
    grNotOr.link(1,2,6);

    List<Arch<Integer,Integer>> list = new ArrayList<>(4);
    list.add(new Arch<>(1,2,6));
    list.add(new Arch<>(2,1,6));
    assertEquals(list,grNotOr.archList());
  }

  @Test
  public void test_nodeList() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);

  	List<Integer> list = new ArrayList<>(2);
  	list.add(1);
  	list.add(2);
  	assertEquals(list,grNotOr.nodeList());
  }

  @Test
  public void test_adjList() throws Exception {
  	grNotOr.add(1);
  	grNotOr.add(2);
  	grNotOr.add(3);

  	grNotOr.link(1,2,6);
  	grNotOr.link(1,3,4);

  	List<Integer> list = new ArrayList<>(2);
  	list.add(2);
  	list.add(3);
  	assertEquals(list,grNotOr.adjList(1));
  }

}





