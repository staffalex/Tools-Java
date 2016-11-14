package de.ast.abstractclass;

public class ConcreteClass extends AbstractClass
{

  @Override
  protected void doTheWork()
  {
    System.out.println("Start the really hard work.");
    try
    {
      Thread.sleep(500);
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Uff, that was hard");

  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    ConcreteClass theWorker = new ConcreteClass();
    theWorker.callTheWork();
  }

}
