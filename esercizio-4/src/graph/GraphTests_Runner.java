package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GraphTests_Runner {
    
  public static void main(String[] args){
    Result result = JUnitCore.runClasses(GraphTests.class);
    for (Failure failure : result.getFailures()){
      System.out.println(failure.toString());
    }
    System.out.println(result.getRunCount()+" Tests "+
                       result.getFailureCount()+" Failed "+
                       result.getIgnoreCount()+" Ignored ("+
                       result.getRunTime()+" ms)");
    System.out.println(result.wasSuccessful());
  } // main

} // class