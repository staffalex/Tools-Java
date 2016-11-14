package de.ast.timezones;

import java.util.TimeZone;

public class MyTimeZones {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    printTimeZones();

  }
  
  public static void printTimeZones()
  {
    for (String tzId : TimeZone.getAvailableIDs())
    {
      System.out.println(tzId);
    }
  }

}
