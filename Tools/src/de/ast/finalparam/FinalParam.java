package de.ast.finalparam;

public class FinalParam
{
  
  public FinalParam()
  {
  }
  
  
  public void doSomething(final StringBuffer buf)
  {
    buf.append("Strange things may happen here! (Why not?)");
  }
  

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    StringBuffer buf = new StringBuffer("Initial content # ");
    FinalParam finalParam = new FinalParam();
    
    finalParam.doSomething(buf);
    
    System.out.println(buf);

  }

}
