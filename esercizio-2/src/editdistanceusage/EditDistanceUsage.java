package editdistanceusage;

import editdistance.EditDistance;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class EditDistanceUsage {

  private static String[] getWords(String filepath) throws IOException {
    if(filepath == null)
      throw new IllegalArgumentException("Filepath cannot be null");

    BufferedReader br = new BufferedReader(new FileReader(filepath));
    String line = null, string = "";
    while((line = br.readLine())!=null)
      string = string + line + "\n";
    br.close();
    return string.split("\\W");
  } // getWords

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

  private static void exec() throws IOException {
    String[] file = getWords("editdistanceusage/resources/correctme.txt");

    for(int i=0; i<file.length; i++) {
      if(file[i].length()==0)
        continue;
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
      }
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  private static void exec2() throws IOException {
    ArrayList<String> file = getWordList("editdistanceusage/resources/correctme.txt");
    BufferedReader br = new BufferedReader(new FileReader("editdistanceusage/resources/dictionary.txt"));
    int[] min_ed = new int[file.size()];
    int[] ed = new int[file.size()];
    
    ArrayList<ArrayList<String>> simWords = new ArrayList<>();
    for(int i=0; i<file.size(); i++)
      simWords.add(new ArrayList<>());

    //aggiunta del primo
    String dict = br.readLine();
    for(int i=0; i<file.size(); i++) {
      min_ed[i] = EditDistance.editDistanceDyn(file.get(i),dict);
      ed[i] = EditDistance.editDistanceDyn(file.get(i),dict);
      simWords.get(i).add(dict);
    }

    //lettura file e aggiunta liste parole simili
    while((dict=br.readLine())!=null) {
      for(int i=0; i<file.size(); i++) {
        ed[i] = EditDistance.editDistanceDyn(file.get(i),dict);
        if(ed[i] < min_ed[i]) {
          simWords.get(i).clear();
          simWords.get(i).add(dict);
          min_ed[i] = ed[i];
        } else if(ed[i]==min_ed[i])
          simWords.get(i).add(dict);
      }
    }
  
    //stampa delle liste
    for(int i=0; i<file.size(); i++)
      System.out.println(file.get(i)+": "+simWords.get(i)+"\n");
  }

  // file e dizionario memorizzati in un array
  private static void exec3() throws IOException {
    String[] file = getWords("editdistanceusage/resources/correctme.txt");
    Object[] dict = getWordList("editdistanceusage/resources/dictionary.txt").toArray();

    for(int i=0; i<file.length; i++) {
      if(file[i].length()==0)
        continue;

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
      }
      System.out.println(file[i]+": "+simWords+"\n");
    }
  }

  // Dizionario in un array ordinato per lunghezza
  private static void exec4() throws IOException {
    String[] file = getWords("editdistanceusage/resources/correctme.txt");
    String[] dict = getWords2("editdistanceusage/resources/dictionary.txt");
    Arrays.sort(dict, new LengthOrder_String());

    for(int i=0; i<file.length; i++) {
      if(file[i].length()==0)
        continue;

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
        else if(ed==0 || dict[j].length()-file[i].length()>ed)
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
    String word = "rirg";
    
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
  // sfrutta un teorema
  private static void exec5() throws IOException {
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
      exec5();
    } catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("Time: "+(System.nanoTime()-begin)/1000000000.0);
  } // main

} // class