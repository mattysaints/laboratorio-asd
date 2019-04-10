package editdistanceusage;

import java.io.Reader;
import editdistance.EditDistance;

public class EditDistanceUsage {

  private static String[] getWords(String filepath) {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");

    BufferedReader br = new FileReader(filepath);
    char peek;
    String content = "";
    
    while((peek=br.read()) != (char)-1)
      content += peek;
    String[] result = content.split("\\W");
    br.close();
    return result;
  } // getWords

  public static void main(String[] args) {

  } // main

} // class