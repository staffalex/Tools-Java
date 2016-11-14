package de.ast;

import java.util.*;

public class Lottozahlen
{
  
  private Random random = new Random();
  
  public Integer getRandomNumber(int min, int max)
  {
    return new Integer(random.nextInt(max - min + 1) + min);
  }
  
  public Set<Integer> createLottozahlen(Date forDate)
  {
    Set<Integer> lottozahlen = new HashSet<Integer>();
    Random random = new Random(forDate.getTime());
    
    while(lottozahlen.size() < 6)
    {
      Integer i = getRandomNumber(1,  49);
      if(!lottozahlen.contains(i))
      {
        lottozahlen.add(i);
      }
    }
    
    return lottozahlen;
  }
  
  public void printNumbersSorted(Set<Integer> numbers)
  {
    
    List<Integer> sortedNumbers = new ArrayList<>(numbers);
    Collections.sort(sortedNumbers);
    
    for(Integer number : sortedNumbers)
    {
      System.out.print(number + ":");
    }
    System.out.println();
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Lottozahlen l = new Lottozahlen();

    Calendar cal = new GregorianCalendar(2016, Calendar.NOVEMBER, 1, 19, 25);
    
    l.printNumbersSorted(l.createLottozahlen(cal.getTime()));

  }

}
