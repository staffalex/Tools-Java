package de.ast.Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.ast.strings.RandomString;


public class LoopWithIterator
{
  
  public void loopByKey(Map<String, String> myMap)
  {
    Iterator<String> iter = myMap.keySet().iterator();
    while (iter.hasNext())
    {
      String s = iter.next();
      String content = myMap.get(s);
      System.out.println(s + " : " + "#" + content + "#");
    }
  }
  
  public Map<String, String> createMap(int count)
  {
    Map<String, String> myMap = new HashMap<String, String>(count);
    RandomString randomString = new RandomString(120);
    
    for( int i = 0; i < count; i++)
    {
      myMap.put("Key_" + Integer.toString(i), randomString.nextString());
    }
    
    // RandomStringUtils geht auch
    
    return myMap;
  }
  


  /**
   * @param args
   */
  public static void main(String[] args)
  {
    LoopWithIterator lwr = new LoopWithIterator();
    Map<String, String> myMap = lwr.createMap(120);
    
    lwr.loopByKey(myMap);
  }

}
