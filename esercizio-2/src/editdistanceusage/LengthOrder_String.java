package editdistanceusage;

import java.util.Comparator;

public class LengthOrder_String implements Comparator<String> {

  /**
   * Override of method compare: ordered by length (ascending), then alphabetic
   * @param s1: first string to compare
   * @param s2: second string to compare
   */
  @Override
  public int compare(String s1, String s2) {
    if(s1.length() < s2.length())
      return -1;
    else if(s1.length() > s2.length())
      return 1;
    else
      return s1.compareTo(s2);
  }
}