package editdistanceusage;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.*;
import editdistance.*;

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

      int length_dict = dict.length;
      int length_file = file.length;

      for(int i=0; i<length_file; i++) {
      	int min_edit = editDistanceDyn(file[i],dict[i]);
      	int result = 0;
      	for(int j = 0; j<length_dict; j++) {
      		result = editDistanceDyn(file[j],file[j]);
      		if(Math.min(min_edit,result) < min_edit)
      			min_edit = result;
      	}
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  } // main

} // class