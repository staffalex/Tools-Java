package de.ast.inheritance;

public class InheritedClazz extends BaseClazz
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    InheritedClazz inheritedClazz = new InheritedClazz();
    
    // Error: System.out.println(inheritedClazz.privMethod(1));
    
    System.out.println(inheritedClazz.protMethod(1));
    System.out.println(inheritedClazz.pubMethod(1));
  }

}
