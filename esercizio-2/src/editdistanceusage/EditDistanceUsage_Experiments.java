package editdistanceusage;

import editdistance.EditDistance;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class EditDistanceUsage {

  private static ArrayList<String> getWordList(String filepath) throws IOException {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");

    ArrayList<String> res = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(filepath));
    String string = "";
    char peek = ' ';
    boolean accumulate = false;
    while((peek=(char)br.read())!=(char)-1) {
      if(peek>='a' && peek<='z' || peek>='A' && peek<='Z') {
        string += peek;
        accumulate = true;
      } else if(accumulate) {
        res.add(string);
        string = "";
        accumulate = false;
      }
    }
    br.close();
    return res;
  } // getWordList

  private static String[] getWords2(String filepath) throws IOException {
    ArrayList<String> tmp = getWordList(filepath);
    String[] res = new String[tmp.size()];
    for(int i=0; i<res.length; i++)
      res[i] = tmp.get(i);
    return res;
  } // getWords2

  // file e dizionario memorizzati in un array
  private static void app1() throws IOException {
    String[] file = getWords2("editdistanceusage/resources/correctme.txt");
    String[] dict = getWords2("editdistanceusage/resources/dictionary.txt");

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
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  private static void app2() throws IOException {
    String[] file = getWords2("editdistanceusage/resources/correctme.txt");

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
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  /*
     Esperimento array ordinato per lunghezza
     per capire quando non e' piu' necessario controllare le parole del dizionario
  */
  private static void experiment() throws IOException {
    // parola da sperimentare
    String word = "supercaliflidoso";
    
    String[] dict = getWords2("editdistanceusage/resources/dictionary.txt");
    Arrays.sort(dict, new LengthOrder_String());
    ArrayList<String> simWords = new ArrayList<>();
    simWords.add(dict[0]);
    int min_ed = EditDistance.editDistanceDyn(word,dict[0]);
    int last_min_ed_length = 0;
    for(int j=0; j<dict.length; j++) {
      int ed = EditDistance.editDistanceDyn(word,dict[j]);
      if(ed < min_ed) {
        min_ed = ed;
        simWords.clear();
        simWords.add(dict[j]);
        last_min_ed_length = dict[j].length();
      } else if(ed==min_ed) {
        simWords.add(dict[j]);
        last_min_ed_length = dict[j].length();
      }
    }
      System.out.println("\n"+word+"\n");
      System.out.println(simWords+"\n");
      System.out.println("last_min_ed_length - word_length: "+(last_min_ed_length-word.length()));
      System.out.println("Min_ed: "+min_ed);
  }

  // Dizionario in un array ordinato per lunghezza
  private static void app3_1() throws IOException {
    String[] file = getWords2("editdistanceusage/resources/correctme.txt");
    String[] dict = getWords2("editdistanceusage/resources/dictionary.txt");
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
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  // Dizionario in un array ordinato per lunghezza
  // sfrutta una proprietà
  private static void app3_2() throws IOException {
    String[] file = getWords2("editdistanceusage/resources/correctme.txt");
    String[] dict = getWords2("editdistanceusage/resources/dictionary.txt");
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
        if(ed==0 || dict[j].length()>file[i].length() && min_ed<ed)
          break;
      }
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  public static void main(String[] args) {
    long begin = System.nanoTime();
    try {
      app3_2();
    } catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("Time: "+(System.nanoTime()-begin)/1000000000.0);
  } // main

} // class