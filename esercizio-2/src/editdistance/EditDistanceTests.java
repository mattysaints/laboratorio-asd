package editdistance;

import static org.junit.Assert.*;
import org.junit.Test;

public class EditDistanceTests {
  
  @Test(expected = IllegalArgumentException.class)
  public void test_EditDistance_null(){
    String s1 = null;
    String s2 = "casi";
    
    int res = EditDistance.editDistance(s1,s2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_EditDistanceDyn_null(){
    String s1 = null;
    String s2 = "casi";
    
    int res = EditDistance.editDistanceDyn(s1,s2);
  }

  @Test
  public void test_EditDistance_zero(){
    String s1 = "pablo";
    String s2 = "pablo";
    
    assertTrue(0 == EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_zero(){
    String s1 = "pablo";
    String s2 = "pablo";
    
    assertTrue(0 == EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistance_one(){
	  String s1 = "casa";
	  String s2 = "casi";
	  
	  assertTrue(1 == EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_one(){
    String s1 = "casa";
    String s2 = "casi";
    
    assertTrue(1 == EditDistance.editDistanceDyn(s1,s2));
  }

  @Test
  public void test_EditDistance_two(){
    String s1 = "casa";
    String s2 = "calo";
    
    assertTrue(2 == EditDistance.editDistance(s1,s2));
  }

  @Test
  public void test_EditDistanceDyn_two(){
    String s1 = "casa";
    String s2 = "calo";
    
    assertTrue(2 == EditDistance.editDistanceDyn(s1,s2));
  }

} // class