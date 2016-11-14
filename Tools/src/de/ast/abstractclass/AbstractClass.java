package de.ast.abstractclass;

public abstract class AbstractClass
{
  protected abstract void doTheWork();
  
  protected void callTheWork()
  {
    long startTime = System.currentTimeMillis();
    
    doTheWork();
    
    System.out.println("Doing something took " + (System.currentTimeMillis() - startTime) + " msecs.");
  }
}
