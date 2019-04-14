package editdistance;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class EditDistanceTests_Runner {
    
  public static void main(String[] args){
    Result result = JUnitCore.runClasses(EditDistanceTests.class);
    for (Failure failure : result.getFailures()){
      System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
  } // main

} // class