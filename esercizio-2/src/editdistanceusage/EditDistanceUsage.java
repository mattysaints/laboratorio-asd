package editdistanceusage;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import editdistance.EditDistance;

public class EditDistanceUsage {

  private static String[] getWords(String filepath) throws IOException {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");

    BufferedReader br = new BufferedReader(new FileReader(filepath));
    char peek;
    String content = "";
    
    while((peek=(char)br.read()) != (char)-1)
      content += peek;
    String[] result = content.split("\\W");
    br.close();
    return result;
  } // getWords

  public static void main(String[] args) {
    try {
      String[] dict = getWords("../resources/dictionary.txt");
      String[] file = getWords("../resources/correctme.txt");
    } catch(IOException e) {
      e.printStackTrace();
    }
  } // main

} // class