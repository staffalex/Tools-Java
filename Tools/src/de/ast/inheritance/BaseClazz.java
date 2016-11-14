package de.ast.inheritance;

public class BaseClazz
{
  private int privMethod(int arg)
  {
    System.out.println("BaseClazz.privMethod : " + arg);
    return arg + 1;
  }
  
  protected int protMethod(int arg)
  {
    System.out.println("protMethod : " + arg);
    return arg + 1;
  }
  
  public int pubMethod(int arg)
  {
    System.out.println("pubMethod : " + arg);
    return arg + 1;
  }
}
