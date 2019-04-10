package editDistance;

public class EditDistanceTests {
  
  @Test
  public void Edit_is_correct(){
	  String s1 = "casa";
	  String s2 = "casi";
	  
	  assertTrue(1 == editDistance(s1,s2));
  }
  
  @Test
  public void EditDyn_is_correct(){
	  String s1 = "caco";
	  String s2 = "cane";
	  
	  assertTrue(2 == editDistanceDyn(s1,s2));
  }
  
  
  
  
} // class