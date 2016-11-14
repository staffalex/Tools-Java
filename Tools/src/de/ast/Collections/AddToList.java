package de.ast.Collections;

import java.util.ArrayList;
import java.util.List;

public class AddToList
{
  
  public static void main(String[] args)
  {
    List<Long> longList = new ArrayList<Long>();

    longList.add(1l);
    longList.add(2l);
    
    longList.add(null);
    
    System.out.println(longList.size());
    
    longList.add(4l);
    
    System.out.println(longList.size());

    longList.add(null);
    
    System.out.println(longList.size());
  }

}
