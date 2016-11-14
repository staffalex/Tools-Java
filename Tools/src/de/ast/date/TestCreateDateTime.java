package de.ast.date;

import static org.junit.Assert.*;

import java.util.*;

import org.apache.commons.lang3.time.*;
import org.junit.*;

public class TestCreateDateTime
{

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testCreateDateTimePlainJava()
  {
    
    System.out.println("Print empty Calendar");
    Calendar cal = new GregorianCalendar();
    cal.clear();
    printCalendar(cal);
    
    System.out.println("Print calendar for 2015-March-19");
    printCalForyyMMdd(2015, Calendar.MARCH, 19);
    
    System.out.println("Print calendar for 11:03:25 a.m. (hourOfDay !)");
    printCalForHHmmss(11, 3, 25);
    
  }
  
  @Test
  public void testCreateDateTimeCommons()
  {
    System.out.println("Print commons calendar for 2015-March-19");
    printCommonsCalForyyMMdd(2015,  Calendar.MARCH, 19);
    
    System.out.println("Print commons calendar for 11:03:25 a.m. (hourOfDay !)");
    printCommonsCalForHHmmss(11, 3, 25);
    
  }
  
  private void printCommonsCalForHHmmss(int hourOfDay, int minute, int second)
  {
    printCommonsCalForValues(0, 0, 0, hourOfDay, minute, second, 0);
  }
  
  private void printCommonsCalForyyMMdd(int year, int month, int dayOfMonth)
  {
    printCommonsCalForValues(year, month, dayOfMonth, 0, 0, 0, 0);
  }
  

  private void printCommonsCalForValues(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int msec)
  {
    Date date = new Date();
    date = DateUtils.setYears(date, year);
    date = DateUtils.setMonths(date, month);
    date = DateUtils.setDays(date, dayOfMonth);
    date = DateUtils.setHours(date, hourOfDay);
    date = DateUtils.setMinutes(date, minute);
    date = DateUtils.setSeconds(date, second);
    date = DateUtils.setMilliseconds(date, msec);
    
    printCalendar(DateUtils.toCalendar(date));
  }
  
  private void printCalForyyMMdd(int year, int month, int day)
  {
    Calendar cal = new GregorianCalendar();  // uses current date and time
    cal.clear();
    cal.set(year, month, day);
    
    // cal.set(year, month, day);
    // cal.set(year, month, day, hourOfDay);
    // cal.set(year, month, day, hourOfDay, minute);
    // cal.set(year, month, day, hourOfDay, minute, second);
    
    printCalendar(cal);
  }
  
  private void printCalForHHmmss(int hourOfDay, int minute, int second)
  {
    Calendar cal = new GregorianCalendar();  // uses current date and time
    cal.clear();
    cal.set(0, 0, 0, hourOfDay, minute, second);
    
    // cal.set(year, month, day);
    // cal.set(year, month, day, hourOfDay);
    // cal.set(year, month, day, hourOfDay, minute);
    // cal.set(year, month, day, hourOfDay, minute, second);
    
    printCalendar(cal);
  }
  
  private void printCalendar(Calendar cal)
  {
    System.out.println("Year : " + cal.get(Calendar.YEAR)  + ", Month " + cal.get(Calendar.MONTH) + ", Day : " + cal.get(Calendar.DAY_OF_MONTH)
        + ", Hour : " + cal.get(Calendar.HOUR_OF_DAY) + ", Minute : " + cal.get(Calendar.MINUTE) + ", Second : " + cal.get(Calendar.SECOND) + ", MSecs : " 
        + cal.get(Calendar.MILLISECOND));
  }

  
}
