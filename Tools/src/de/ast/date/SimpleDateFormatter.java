package de.ast.date;

import java.text.*;
import java.util.*;

import org.apache.commons.lang3.time.*;

public class SimpleDateFormatter
{

  public static void main(String[] args) throws Exception
  {
    // TODO Auto-generated method stub
    // This is how to get today's date in Java
    Date today = new Date();

    System.out.println("Today is : " + today);

//    String pattern = "dd-MM-yyyy";
//    long parsed = printDate(today.getTime(), pattern);
//    printDate(parsed + 555, pattern);
//
//    pattern = "dd/MM/yy";
//    parsed = printDate(today.getTime(), pattern);
//    printDate(parsed + 555, pattern);
//
//    pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
//    parsed = printDate(today.getTime(), pattern);
//    printDate(parsed + 555, pattern);
//
//    pattern = "dd-MM-yy:HH:mm:ss:SSS";
//    parsed = printDate(today.getTime(), pattern);
//    printDate(parsed + 555, pattern);
    
    String[] patterns = {"dd-MM-yyyy", "dd/MM/yy", "yyyy-MM-dd'T'HH:mm:ssZ", "dd-MM-yy:HH:mm:ss:SSS"};
    commonsPrintDate(today.getTime(), patterns);
  }
  // 2014-09-26T08:24:39+02:00

  private static long printDate(long msecs, String pattern) throws ParseException
  {
    // formatting date in Java using SimpleDateFormat
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    String formattedDate = sdf.format(msecs);
    
    sdf.applyPattern(pattern);
    Date parsedDate = sdf.parse(formattedDate);
    
    System.out.println("Plain Java: Original time : " + msecs + ", parsed time : " + parsedDate.getTime() + ", pattern : " + pattern + ", formatted time : " + formattedDate);
    
    return parsedDate.getTime();
    
  }
  
  private static void commonsPrintDate(long msecs, String[] patterns) throws Exception 
  {
    
    for (String pattern : patterns)
    {
      String formattedDate = DateFormatUtils.format(msecs, pattern);
      
      Date parsedDate = DateUtils.parseDate(formattedDate, patterns);
    
      System.out.println("Commons: Original time : " + msecs + ", parsed time : " + parsedDate.getTime() + ", pattern : " + pattern + ", formatted time : " + formattedDate);
    }
  }
  
  
}

