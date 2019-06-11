package editdistanceusage;

import editdistance.EditDistance;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class EditDistanceUsage {

  /**
   * Returns an array of strings loaded from a file
   * @param filepath: path of the file
   */
  private static String[] loadWords(String filepath) throws IOException {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");
    ArrayList<String> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filepath));
    String string = "";
    char peek = ' ';
    boolean accumulate = false;
    while((peek=(char)br.read())!=(char)-1) {
      if(peek>='a' && peek<='z' || peek>='A' && peek<='Z') {
        string += peek;
        accumulate = true;
      } else if(accumulate) {
        list.add(string);
        string = "";
        accumulate = false;
      }
    }
    br.close();

    String[] res = new String[list.size()];
    for(int i=0; i<res.length; i++)
      res[i] = list.get(i);
    return res;
  } // loadWords

  /**
   * @param args: the command line arguments:
   *  - args[0] should contain the filepath of the sentence to correct
   *  - args[1] should contain the filepath of the dictionary
   */
  public static void main(String[] args) throws Exception, IOException, IllegalArgumentException{
    if(args.length < 2)
      throw new Exception("Usage: EditDistanceUsage <file_path> <dictionary_path>");
    long begin = System.nanoTime();
    String[] file = loadWords(args[0]);
    String[] dict = loadWords(args[1]);
    Arrays.sort(dict, new LengthOrder_String());

    for(int i=0; i<file.length; i++) {
      int min_ed = EditDistance.editDistanceDyn(file[i],dict[0]);
      ArrayList<String> simWords = new ArrayList<>();
      simWords.add(dict[0]);
      
      for(int j=1; j<dict.length; j++) {
        int ed = EditDistance.editDistanceDyn(file[i],dict[j]);
        if(ed < min_ed) {
          min_ed = ed;
          simWords.clear();
          simWords.add(dict[j]);
        } else if(ed==min_ed)
          simWords.add(dict[j]);
        if(ed==0)
          break;
      }
      System.out.println(file[i]+": "+simWords);
    }
    System.out.println("-----------------------------------\n"
                      +"EditDistanceUsage ("+((System.nanoTime()-begin)/1000000000.0)+" s)");
  } // main

} // class