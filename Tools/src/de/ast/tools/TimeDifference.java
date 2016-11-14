package de.ast.tools;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;

public class TimeDifference
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    TimeDifference td = new TimeDifference();
    
    String dateStart = "11/03/14 09:29:58";
    String dateStop = "11/03/15 09:38:43";
    
    td.diff(dateStart, dateStop);

  }
  
  public void diff(String time1, String time2)
  {

    // Custom date format
    SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    
    Date date1 = null;
    Date date2 = null;
    
    try
    {
      date1 = format.parse(time1);
      date2 = format.parse(time2);
      
      long diff = date2.getTime() - date1.getTime();//as given

      long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
      long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
      
      System.out.println("Seconds : " + seconds);
      System.out.println("Minutes : " + minutes);
    }
    catch(ParseException e)
    {
      e.printStackTrace();
    }
    
  }
  

}
