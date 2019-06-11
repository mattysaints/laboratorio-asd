package editdistanceusage;

import editdistance.EditDistance;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class EditDistanceUsage_Experiments {

  // restituisce un array di stringhe dato il file
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

  // file e dizionario memorizzati in un array
  private static void first_approach() throws IOException {
    String[] file = loadWords("editdistanceusage/resources/correctme.txt");
    String[] dict = loadWords("editdistanceusage/resources/dictionary.txt");

    for(int i=0; i<file.length; i++) {
      int min = EditDistance.editDistanceDyn(file[i],(String)dict[0]);
      ArrayList<String> simWords = new ArrayList<>();
      simWords.add((String)dict[0]);
      for(int j=1; j<dict.length; j++) {
        int ed = EditDistance.editDistanceDyn(file[i],(String)dict[j]);
        if(ed < min) {
          min = ed;
          simWords.clear();
          simWords.add((String)dict[j]);
        } else if(ed==min)
          simWords.add((String)dict[j]);
        if(ed==0)
          break;
      }
      System.out.println(file[i]+": "+simWords);
    }
  }

  // file memorizzato in un array, dizionario letto parola per parola
  private static void second_approach() throws IOException {
    String[] file = loadWords("editdistanceusage/resources/correctme.txt");

    for(int i=0; i<file.length; i++) {
      BufferedReader br = new BufferedReader(new FileReader("editdistanceusage/resources/dictionary.txt"));
      String dict = br.readLine();
      int min = EditDistance.editDistanceDyn(file[i],dict);
      ArrayList<String> simWords = new ArrayList<>();
      simWords.add(dict);
      while((dict=br.readLine())!=null) {
        int ed = EditDistance.editDistanceDyn(file[i],dict);
        if(ed < min) {
          min = ed;
          simWords.clear();
          simWords.add(dict);
        } else if(ed==min)
          simWords.add(dict);
        if(ed==0)
          break;
      }
      System.out.println(file[i]+": "+simWords);
    }
  }

  // File e dizionario memorizzati: dizionario in un array ordinato per lunghezza
  private static void third_approach() throws IOException {
    String[] file = loadWords("editdistanceusage/resources/correctme.txt");
    String[] dict = loadWords("editdistanceusage/resources/dictionary.txt");
    Arrays.sort(dict, new LengthOrder_String());

    for(int i=0; i<file.length; i++) {
      int min_ed = EditDistance.editDistanceDyn(file[i],dict[0]);
      int max_ed = min_ed;
      ArrayList<String> simWords = new ArrayList<>();
      simWords.add(dict[0]);
      for(int j=1; j<dict.length; j++) {
        int ed = EditDistance.editDistanceDyn(file[i],dict[j]);
        max_ed = Math.max(max_ed,ed);
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
  }

  // File e dizionario memorizzati: dizionario in un array ordinato per lunghezza. Sfrutta una proprietà
  private static void fourth_approach() throws IOException {
    String[] file = loadWords("editdistanceusage/resources/correctme.txt");
    String[] dict = loadWords("editdistanceusage/resources/dictionary.txt");
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
        if(ed==0 || dict[j].length()>file[i].length() && min_ed<ed) // proprietà
          break;
      }
      System.out.println(file[i]+": "+simWords);
    }
  }

  //main
  public static void main(String[] args) throws Exception {
    long begin = System.nanoTime();

    first_approach();
    //second_approach();
    //third_approach();
    //fourth_approach();

    System.out.println("-----------------------------------\n"
                      +"EditDistanceUsage ("+((System.nanoTime()-begin)/1000000000.0)+" s)");
  } // main

} // class