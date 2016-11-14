package de.ast.date;

import java.util.*;

import org.apache.commons.lang3.time.*;
import org.junit.*;

public class TestGetSingleValuesFromDateTime
{
  
  Calendar globalCal = new GregorianCalendar();

  @Before
  public void setUp() throws Exception
  {
    globalCal.clear();
    globalCal.set(2015, Calendar.MARCH, 19, 11, 49, 33);
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testGetValuesPlainJava()
  {
    printCalendar(globalCal);
  }
  
  @Test
  public void testTruncateDate()
  {
    Date myDate = DateUtils.truncate(new Date(), Calendar.DATE);
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(myDate);
    printCalendar(cal);
  }

  @Test
  public void testGetValuesCommons()
  {
    Calendar cal = DateUtils.truncate(globalCal, Calendar.YEAR);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.MONTH);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.DAY_OF_MONTH);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.HOUR_OF_DAY);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.MINUTE);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.SECOND);
    printCalendar(cal);
    
    cal = DateUtils.truncate(globalCal, Calendar.MILLISECOND);
    printCalendar(cal);
  }

  private void printCalendar(Calendar cal)
  {
    System.out.println("Year : " + cal.get(Calendar.YEAR)  + ", Month " + cal.get(Calendar.MONTH) + ", Day : " + cal.get(Calendar.DAY_OF_MONTH)
        + ", Hour : " + cal.get(Calendar.HOUR_OF_DAY) + ", Minute : " + cal.get(Calendar.MINUTE) + ", Second : " + cal.get(Calendar.SECOND) + ", MSecs : " 
        + cal.get(Calendar.MILLISECOND));
  }

}
