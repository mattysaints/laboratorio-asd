package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class GraphTests {

  private Graph<Integer,Integer> grNotOr;
  private Graph<Integer,Integer> grOr;

  @Before
  public void createGraph() {
    grNotOr = new Graph<>(false);
    grOr = new Graph<>(true);
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
    assertTrue(grOr.isOriented());
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
  public void test_link_or() throws Exception {
  	grOr.add(3);
  	grOr.add(5);
  	grOr.link(3,5,10);
    assertTrue(grOr.containsLink(3,5));
  	assertFalse(grOr.containsLink(5,3));
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
  public void test_del_linked_or() throws Exception {
    grOr.add(5);
    grOr.add(7);
    grOr.link(5,7,10);
    grOr.del(5);
    assertFalse(grOr.containsNode(5));
    assertFalse(grOr.containsLink(5,7));
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
  public void test_unlink_or() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.link(1,2,6);
    grOr.unlink(1,2);
    assertFalse(grOr.containsLink(1,2));
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
  public void test_numLinks_or() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.add(4);
    grOr.link(1,2,6);
    grOr.link(2,1,6);
    grOr.link(2,4,5);
    assertEquals(3,grOr.numLinks());
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
  public void test_archList_or() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.add(4);
    grOr.link(1,2,6);
    grOr.link(4,2,2);

    List<Arch<Integer,Integer>> actual = grOr.archList();
    assertTrue(actual.contains(new Arch<>(1,2,6)));
    assertTrue(actual.contains(new Arch<>(4,2,2)));
  }

  @Test
  public void test_archList_notOr() throws Exception {
    grNotOr.add(1);
    grNotOr.add(2);
    grNotOr.add(3);
    grNotOr.link(1,2,6);
    grNotOr.link(2,3,5);

    List<Arch<Integer,Integer>> actual = grNotOr.archList();
    assertTrue(actual.contains(new Arch<>(1,2,6)));
    //assertTrue(actual.contains(new Arch<>(2,1,6)));
    assertTrue(actual.contains(new Arch<>(2,3,5)));
    //assertTrue(actual.contains(new Arch<>(3,2,5)));
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





