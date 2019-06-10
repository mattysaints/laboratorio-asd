package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.lang.Comparable;

public class GraphTests {

  // support class to check archList
  private class ArchComparator<T extends Comparable<T>,E extends Number & Comparable<E>> implements Comparator<Arch<T,E>> {
    @Override
    public int compare(Arch<T,E> a, Arch<T,E> b) {
      int compare = a.weight().compareTo(b.weight());
      return compare==0 ? compare : a.start().compareTo(b.start());
    }
  }

  private Graph<Integer,Integer> grNotOr;
  private Graph<Integer,Integer> grOr;
  private Graph<String,Double> grStr;


  @Before
  public void createGraph() {
    grNotOr = new Graph<>(false);
    grOr = new Graph<>(true);
    grStr = new Graph<>(true);
  }

  @Test(expected=GraphException.class)
  public void test_add_null() throws Exception {
    grNotOr.add(null);
  }

  @Test
  public void test_add() throws Exception {
    assertTrue(grOr.add(3));
    assertTrue(grOr.containsNode(3));
  }

  @Test
  public void test_add_alreadyIn() throws Exception {
    grNotOr.add(3);
    assertFalse(grNotOr.add(3));
  }

  @Test(expected=GraphException.class)
  public void test_link_null() throws Exception {
    grNotOr.add(3);
    grNotOr.add(5);
    grNotOr.link(null,5,10);
  }

  @Test
  public void test_link_notOr() throws Exception {
  	grNotOr.add(3);
  	grNotOr.add(5);
  	assertTrue(grNotOr.link(3,5,10));
  	assertTrue(grNotOr.containsLink(3,5));
    assertTrue(grNotOr.containsLink(5,3));
  }

  @Test
  public void test_link_or() throws Exception {
  	grOr.add(3);
  	grOr.add(5);
  	assertTrue(grOr.link(3,5,10));
    assertTrue(grOr.containsLink(3,5));
  	assertFalse(grOr.containsLink(5,3));
  }

   @Test 
  public void test_isOriented() {
    assertFalse(grNotOr.isOriented());
    assertTrue(grOr.isOriented());
  }

  @Test
  public void test_containsNode_null() throws Exception {
    assertFalse(grNotOr.containsNode(null));
  }

  @Test
  public void test_containsNode_notIn() throws Exception {
    grNotOr.add(6);
    assertFalse(grNotOr.containsNode(2));
  }

  @Test
  public void test_containsNode() throws Exception {
    grNotOr.add(6);
    assertTrue(grNotOr.containsNode(6));
  }

  @Test
  public void test_containsLink_null() throws Exception {
    grOr.add(6);
    grOr.add(3);
    grOr.link(6,3,2);
    assertFalse(grOr.containsLink(6,null));
  }

  @Test
  public void test_containsLink() throws Exception {
    grOr.add(6);
    grOr.add(3);
    grOr.link(6,3,2);
    assertTrue(grOr.containsLink(6,3));
  }

  @Test(expected=GraphException.class)
  public void test_del_null() throws Exception {
    grNotOr.add(5);
    grNotOr.del(null);
  }

  @Test
  public void test_del_notIn() throws Exception {
    grNotOr.add(5);
    assertFalse(grNotOr.del(3));
  }

  @Test
  public void test_del() throws Exception {
  	grNotOr.add(5);
  	assertTrue(grNotOr.del(5));
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

  @Test(expected=GraphException.class)
  public void test_unlink_null() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.link(1,2,6);
    grOr.unlink(null,2);
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
  public void test_unlink_notIn() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.link(1,2,6);
    assertFalse(grOr.unlink(3,2));
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
  public void test_nodeList() throws Exception {
    grNotOr.add(1);
    grNotOr.add(2);

    List<Integer> expected = new ArrayList<>(2);
    List<Integer> actual = grNotOr.nodeList();
    expected.add(1);
    expected.add(2);
    expected.sort(null);
    actual.sort(null);
    assertEquals(expected,actual);
  }

  @Test
  public void test_archList_or() throws Exception {
    grOr.add(1);
    grOr.add(2);
    grOr.add(4);
    grOr.link(1,2,6);
    grOr.link(4,2,2);

    List<Arch<Integer,Integer>> actual = grOr.archList();
    List<Arch<Integer,Integer>> expected = new ArrayList<>();
    ArchComparator<Integer,Integer> ac = new ArchComparator<>();
    expected.add(new Arch<>(1,2,6));
    expected.add(new Arch<>(4,2,2));
    actual.sort(ac);
    expected.sort(ac);
    assertEquals(expected,actual);
  }

  @Test
  public void test_archList_notOr() throws Exception {
    grNotOr.add(1);
    grNotOr.add(2);
    grNotOr.add(3);
    grNotOr.link(1,2,6);
    grNotOr.link(2,3,5);

    List<Arch<Integer,Integer>> actual = grNotOr.archList();
    List<Arch<Integer,Integer>> expected = new ArrayList<>();
    ArchComparator<Integer,Integer> ac = new ArchComparator<>();
    expected.add(new Arch<>(1,2,6));
    expected.add(new Arch<>(2,3,5));
    actual.sort(ac);
    expected.sort(ac);
    assertEquals(expected,actual);
  }

  @Test(expected=GraphException.class)
  public void test_adjList_null() throws Exception {
    grNotOr.adjList(null);
  }

  @Test
  public void test_adjList() throws Exception {
    grNotOr.add(1);
    grNotOr.add(2);
    grNotOr.add(3);
    grNotOr.link(1,2,6);
    grNotOr.link(1,3,4);

    List<Integer> expected = new ArrayList<>(2);
    List<Integer> actual = grNotOr.adjList(1);
    expected.add(2);
    expected.add(3);
    expected.sort(null);
    actual.sort(null);
    assertEquals(expected,actual);
  }

  @Test(expected=GraphException.class)
  public void test_weight_null() throws Exception {
  	grNotOr.add(4);
  	grNotOr.add(5);
  	grNotOr.link(4,5,7);
    grNotOr.weight(null,5);
  }

  @Test(expected=GraphException.class)
  public void test_weight_notIn() throws Exception {
    grNotOr.add(4);
    grNotOr.add(5);
    grNotOr.link(4,5,7);
    grNotOr.weight(4,8);
  }

  @Test
  public void test_weight() throws Exception {
    grNotOr.add(4);
    grNotOr.add(5);
    grNotOr.link(4,5,7);
    assertEquals((Integer)7,grNotOr.weight(4,5));
  }


  // Tests graph of strings

  @Test
  public void test_add_str() throws Exception {
    assertTrue(grStr.add("albero"));
    assertTrue(grStr.containsNode("albero"));
  }

  @Test
  public void test_link_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    assertTrue(grStr.link("albero","casa",12.3));
    assertTrue(grStr.containsLink("albero","casa"));
  }

  @Test
  public void test_containsNode_str() throws Exception {
    grStr.add("casa");
    assertTrue(grStr.containsNode("casa"));
  }

  @Test
  public void test_containsLink_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.link("albero","casa",2.3);
    assertTrue(grStr.containsLink("albero","casa"));
  }

  @Test
  public void test_del_str() throws Exception {
    grStr.add("albero");
    assertTrue(grStr.del("albero"));
    assertFalse(grStr.containsNode("albero"));
  }

  @Test
  public void test_del_linked_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.link("albero","casa",10.2);
    grStr.del("casa");
    assertFalse(grStr.containsNode("casa"));
    assertFalse(grStr.containsLink("albero","casa"));
  }

  @Test
  public void test_unlink_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.link("albero","casa",6.8);
    grStr.unlink("albero","casa");
    assertFalse(grStr.containsLink("albero","casa"));
  }

  @Test
  public void test_nodeList_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");

    List<String> expected = new ArrayList<>(2);
    List<String> actual = grStr.nodeList();
    expected.add("albero");
    expected.add("casa");
    expected.sort(null);
    actual.sort(null);
    assertEquals(expected,actual);
  }

  @Test
  public void test_archList_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.add("divano");
    grStr.link("albero","casa",6.3);
    grStr.link("divano","casa",2.7);

    List<Arch<String,Double>> actual = grStr.archList();
    List<Arch<String,Double>> expected = new ArrayList<>();
    ArchComparator<String,Double> ac = new ArchComparator<>();
    expected.add(new Arch<>("albero","casa",6.3));
    expected.add(new Arch<>("divano","casa",2.7));
    actual.sort(ac);
    expected.sort(ac);
    assertEquals(expected,actual);
  }

  @Test
  public void test_adjList_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.add("divano");
    grStr.link("albero","casa",6.2);
    grStr.link("albero","divano",4.9);

    List<String> expected = new ArrayList<>(2);
    List<String> actual = grStr.adjList("albero"); 
    expected.add("casa");
    expected.add("divano");
    expected.sort(null);
    actual.sort(null);
    assertEquals(expected,actual);
  }

  @Test
  public void test_weight_str() throws Exception {
    grStr.add("albero");
    grStr.add("casa");
    grStr.link("albero","casa",7.3);
    assertEquals((Double)7.3,grStr.weight("albero","casa"));
  }  

}
