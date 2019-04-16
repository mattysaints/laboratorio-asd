package editdistanceusage;

import editdistance.EditDistance;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.*;

public class EditDistanceUsage {

  private static String[] getWords(String filepath) throws IOException {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");

    BufferedReader br = new BufferedReader(new FileReader(filepath));
    String line = null, string = "";
    int i=0;
    while((line = br.readLine())!=null && i<100000) {
      string = string + line + "\n";
      i++;
    }
    br.close();
    return string.split("\\W");
  } // getWords

  public static void main(String[] args) {
    try {
      String[] file = getWords("editdistanceusage/resources/correctme.txt");
      String[] dict = getWords("editdistanceusage/resources/dictionary.txt");

      for(int i=0; i<file.length; i++) {
        if(file[i].length()==0)
          continue;

        int min = EditDistance.editDistanceDyn(file[i],dict[0]);
        ArrayList<String> simWords = new ArrayList<>();
        simWords.add(dict[0]);
        for(int j=1; j<dict.length; j++) {
          int ed = EditDistance.editDistanceDyn(file[i],dict[j]);
          if(ed < min) {
            simWords.clear();
            simWords.add(dict[j]);
          } else if(ed==min)
            simWords.add(dict[j]);
        }
        System.out.println(file[i]+": "+simWords.toString()+"\n");
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  } // main

} // class