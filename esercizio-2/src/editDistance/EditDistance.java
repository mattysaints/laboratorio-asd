package src.editDistance;

public class EditDistance {

  public static int editDistance(String s1, String s2) throws IllegalArgumentException {
    if(s1!=null && s2!=null) {
      
      if(s1.length() == 0)
        return s2.length();
      else if(s2.length() == 0)
        return s1.length();
      else {
        String rest_s1 = s1.substring(1);
        String rest_s2 = s2.substring(1);

        int d_noOp = (s1.charAt(0) == s2.charAt(0)) ? editDistance(rest_s1, rest_s2) : Integer.MAX_VALUE;
        int d_canc = 1 + editDistance(s1, rest_s2);
        int d_ins = 1 + editDistance(rest_s1, s2);
        int d_repl = 1 + editDistance(rest_s1, rest_s2);

        return Math.min(Math.min(Math.min(d_noOp,d_canc),d_ins),d_repl);
      }
    } else
      throw new IllegalArgumentException("The string parameters cannot be null");
  }

  public static int editDistanceDyn(String s1, String s2) throws IllegalArgumentException {
    if(s1!=null && s2!=null) {
      int[][] ed = new int[s1.length()+1][s2.length()+1];
      for(int i=0; i<ed.length; i++)
        for(int j=0; j<ed[i].length; j++)
          ed[i][j] = -1;
      return editDistanceDyn(s1,s2,ed);
    } else
      throw new IllegalArgumentException("The string parameters cannot be null");
  }

  private static int editDistanceDyn(String s1, String s2, int[][] ed) {
    if(s1.length() == 0)
      return s2.length();
    else if(s2.length() == 0)
      return s1.length();
    else {
      String rest_s1 = s1.substring(1);
      String rest_s2 = s2.substring(1);

      if(s1.charAt(0) == s2.charAt(0))
        return editDistanceDyn(rest_s1, rest_s2);
      else {
        int i = ed.length-s1.length();
        int j = ed[0].length-s2.length();

        if(ed[i][j+1] == -1)
          ed[i][j+1] = 1 + editDistanceDyn(s1, rest_s2, ed);
        if(ed[i+1][j] == -1)
          ed[i+1][j] = 1 + editDistanceDyn(rest_s1, s2, ed);
        if(ed[i+1][j+1] == -1)
          ed[i+1][j+1] = 1 + editDistanceDyn(s1, rest_s2, ed);

        ed[i][j] = Math.min(Math.min(ed[i][j+1],ed[i+1][j]),ed[i+1][j+1]);
        return ed[i][j];
      }
    }
  }
}