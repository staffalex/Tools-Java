package de.ast.numbers;

public class NumberCompare
{
  
  public static boolean equalsFloat(float f1, float f2)
  {
    return Float.compare(f1, f2) == 0;
  }
  
  
  public static boolean equalsStringAsFloat(String s1, String s2)
  {
    float f1 = Float.valueOf(s1);
    float f2 = Float.valueOf(s2);
    
    return Float.compare(f1, f2) == 0;
  }
  
  public static boolean equalsStringAsInt(String s1, String s2)
  {
    return true;
  }
  

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println(NumberCompare.equalsFloat(1f, 1.0000f));
    System.out.println(NumberCompare.equalsStringAsFloat("1", "1.000"));
    
  }

}
