package editdistance;

import static org.junit.Assert.*;
import org.junit.Test;

public class EditDistanceTests {

  /**
   * Tests of editDistanceDyn
   */
  
  @Test(expected = IllegalArgumentException.class)
  public void test_EditDistance_null(){
    String s1 = null;
    String s2 = "casi";
    
    int res = EditDistance.editDistance(s1,s2);
  }

  @Test
  public void test_EditDistance_bothEmptyStrings(){
    String s1 = "";
    String s2 = "";
    
    assertEquals(0,EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_emptyString(){
    String s1 = "";
    String s2 = "casi";
    
    assertEquals(s2.length(),EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_zero(){
    String s1 = "pablo";
    String s2 = "pablo";
    
    assertEquals(0,EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_one(){
    String s1 = "casa";
    String s2 = "casi";
    
    assertEquals(1,EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_two(){
    String s1 = "casa";
    String s2 = "calo";
    
    assertEquals(2,EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_three(){
    String s1 = "casa";
    String s2 = "palo";
    
    assertEquals(3,EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistance_four(){
    String s1 = "casa";
    String s2 = "lime";
    
    assertEquals(4,EditDistance.editDistance(s1,s2));
  }

  /**
   * Tests of editDistanceDyn
   */

  @Test(expected = IllegalArgumentException.class)
  public void test_EditDistanceDyn_null(){
    String s1 = null;
    String s2 = "casi";
    
    int res = EditDistance.editDistanceDyn(s1,s2);
  }

  @Test
  public void test_EditDistanceDyn_bothEmptyStrings(){
    String s1 = "";
    String s2 = "";
    
    assertEquals(0,EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_emptyString(){
    String s1 = "";
    String s2 = "casi";
    
    assertEquals(s2.length(),EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_zero(){
    String s1 = "pablo";
    String s2 = "pablo";
    
    assertEquals(0,EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_one(){
    String s1 = "casa";
    String s2 = "casi";
    
    assertEquals(1,EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_two(){
    String s1 = "casa";
    String s2 = "calo";
    
    assertEquals(2,EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_three(){
    String s1 = "casa";
    String s2 = "palo";
    
    assertEquals(3,EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_four(){
    String s1 = "casa";
    String s2 = "lime";
    
    assertEquals(4,EditDistance.editDistanceDyn(s1,s2));
  }

} // class