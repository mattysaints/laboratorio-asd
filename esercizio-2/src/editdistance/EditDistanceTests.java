package editdistance;

import static org.junit.Assert.*;
import org.junit.Test;

public class EditDistanceTests {
  
  @Test
  public void Edit_is_correct(){
	  String s1 = "casa";
	  String s2 = "casi";
	  
	  assertTrue(1 == editDistance(s1,s2));
  }
  
  @Test
  public void EditDyn_is_correct(){
	  String s1 = "acqua";
	  String s2 = "accua";
	  
	  assertTrue(2 == editDistanceDyn(s1,s2));
  }

  @Test
  public void Same_String(){
  	String s1 = "pablo";
	  String s2 = "pablo";
	  
	  assertTrue(0 == editDistanceDyn(s1,s2));
  }

  @Test
  public void Same_String(){
  	String s1 = "";
	  String s2 = "";
	  
	  assertEquals(s1,s2);
  }

  @Test
  public void is_notNull(){
  	String s1 = "pablo";
	  
	  assertNotNull(s1);
  }

  @Test
  public void are_Null(){
  	String s1;
  	String s2;
	  
	  assertNull(s1,s2);
  }  
} // class