package de.ast.strings;

public class NumberToString
{
  
  public NumberToString()
  {
  }
  
  public static void printAsString(Double dValue)
  {
    System.out.println("dValue : " + dValue.toString());
  }
  
  public static void printAsString(String value, String format)
  {
    System.out.println(String.format(format, value));
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    NumberToString.printAsString(1.0);
    NumberToString.printAsString(1.5);
    NumberToString.printAsString(-2.5);
    NumberToString.printAsString(1E2);
    
    printAsString("1.000", "");
    printAsString("1.000", "");
    printAsString("1.000", "");
    printAsString("1.000", "");
    
    System.out.println("toString : " + 1 );
    System.out.println("toString : " + 10 );
    System.out.println("toString : " + 100 );
    System.out.println("toString : " + 1000 );
    
  }

}
