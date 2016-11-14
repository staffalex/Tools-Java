package de.ast.Collections;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoopTest
{
  private static final int limit = 10000000;
  private static final int runLoops = 10;
  
  private long startTime;
  private long endTime;
  
  private static List<Integer> intList;
  
  private static List<String> strList;
  
  private static List<Long> runtimes;
  
  @Before
  public void setUp()
  {
    intList = new ArrayList<Integer>(limit);
    strList = new ArrayList<String>(limit);
    
    runtimes = new ArrayList<Long>();
  }
  
  @After
  public void tearDown()
  {
    intList.clear();
    strList.clear();
  }
  
  private void prepareIntList()
  {
    List<Integer> otherList = new ArrayList<Integer>(limit);
    intList = otherList;
    for(int i = 0; i < limit; i++)
    {
      intList.add(i);
    }
  }
  
  private void prepareStrList()
  {
    List<String> otherList = new ArrayList<String>(limit);
    strList = otherList;
    for(int i = 0; i < limit; i++)
    {
      strList.add("Word" + i);
    }
  }
  
  @Test
  public void testIntListLoops()
  {
    System.out.println("IntListLoops");
    for(int i = 0; i < runLoops; i++)
    {
      loopOverIntegerList();
      printRuntimes(i + " : ");
      runtimes.clear();
    }
  }
  
  @Test
  public void testStrListLoops()
  {
    System.out.println("StrListLoops");
    for(int i = 0; i < runLoops; i++)
    {
      loopOverStringList();
      printRuntimes(i + " : ");
      runtimes.clear();
    }
  }
  
  private void loopOverIntegerList()
  {
    startTime = Calendar.getInstance().getTimeInMillis();
    prepareIntList();
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);
    
    loopOverList(intList);
  }
  
  private void loopOverStringList()
  {
    startTime = Calendar.getInstance().getTimeInMillis();
    prepareStrList();
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);
    
    loopOverList(strList);
  }
  
  private <T> void loopOverList(List<T> loopList)
  {
    //Type 1
    startTime = Calendar.getInstance().getTimeInMillis();
    for(T t : loopList)
    {
        //
    }
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);

    //Type 2
    startTime = Calendar.getInstance().getTimeInMillis();
    for(int j = 0; j < loopList.size() ; j++)
    {
        //
    }
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);

    //Type 3
    startTime = Calendar.getInstance().getTimeInMillis();
    int size = loopList.size();
    for(int j = 0; j < size ; j++)
    {
        //System.out.println(j);
    }
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);

    //Type 4
    startTime = Calendar.getInstance().getTimeInMillis();
    for(int j = loopList.size(); j > size ; j--)
    {
      //System.out.println(j);
    }
    endTime = Calendar.getInstance().getTimeInMillis();
    runtimes.add(endTime - startTime);
  }
  
  private void printRuntimes(String prefix)
  {
    StringBuffer sb = new StringBuffer();
    sb.append(prefix);

    int size = runtimes.size();
    for(int i = 0; i < size; i++)
    {
      if( i == 0)
      {
        sb.append("Runtime prepare : ");
      }
      else
      {
        sb.append(", runtime" + i + " : ");
      }
      sb.append(getRuntimeFormatted(runtimes.get(i)));
    }
    
    System.out.println(sb);
  }
  
  private String getRuntimeFormatted(long runtime)
  {
    return String.format("% 5d", runtime);
  }
  
}

