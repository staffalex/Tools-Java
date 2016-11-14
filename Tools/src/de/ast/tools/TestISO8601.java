package de.ast.tools;

import java.text.*;
import java.util.*;

import javax.xml.bind.*;

public class TestISO8601
{
  public static void main(String[] args)
  {
    
    datePrint(parse("2012-10-01T19:30:00+02:00")); // UTC+2
    datePrint(parse("2012-10-01T19:30:00Z")); // UTC
    datePrint(parse("2012-10-01T19:30:00")); // Local
  }

  public static Date parse(final String str)
  {
    Calendar c = DatatypeConverter.parseDateTime(str);
    System.out.println(str + "\t" + (c.getTime().getTime() / 1000));
    return c.getTime();
  }
  
  public static void datePrint(Date d)
  {
    String format = "yyyy-MM-dd'T'HH:mm:ssZ";
    String sDate;
    
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sDate = sdf.format(d);
    System.out.println("Today in " + format + "##" + sDate);    
  }
  
}
