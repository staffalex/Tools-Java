package de.ast;

public class GetEnv
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.println(System.getenv("SESSION"));
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("USER"));
  }

}
