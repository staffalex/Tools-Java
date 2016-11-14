package de.ast.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateWorkingDays
{

  private Map<Integer, List<Integer>> holidays;

  public int getCurrentWorkingDay()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTime(new Date());

    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = 1;

    int endYear = cal.get(Calendar.YEAR);
    int endMonth = cal.get(Calendar.MONTH);
    int endDay = cal.get(Calendar.DAY_OF_MONTH);

    int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

    return getNumberOfWorkingDays(startYear, startMonth, startDay, endYear, endMonth, endDay);
  }

  private void initHolidays(List<String> days)
  {
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    holidays = new HashMap<Integer, List<Integer>>();

    for (String day : days)
    {

      Date date;
      try
      {
        date = df.parse(day);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        addHoliday(holidays, cal);
      }
      catch (ParseException e)
      {
        // TODO Auto-generated catch block
        // report message and skip
      }
    }

  }

  private void addHoliday(Map<Integer, List<Integer>> hDays, Calendar cal)
  {
    int year = cal.get(Calendar.YEAR);
    List<Integer> listOfDays = hDays.get(year);
    if (listOfDays == null)
    {
      listOfDays = new ArrayList<Integer>();
      hDays.put(year, listOfDays);
    }

    listOfDays.add(cal.get(Calendar.DAY_OF_YEAR));
  }

  private boolean isWorkingDay(Calendar cal)
  {
    if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
        && !dayIsHoliday(cal))
    {
      return true;
    }
    return false;
  }

  private boolean dayIsHoliday(Calendar cal)
  {
    if (holidays != null && holidays.size() > 0)
    {
      int year = cal.get(Calendar.YEAR);
      List<Integer> hDays = holidays.get(year);

      Integer calDay = cal.get(Calendar.DAY_OF_YEAR);

      if (hDays != null)
      {
        for (Integer hDay : hDays)
        {
          if (calDay.equals(hDay))
          {
            return true;
          }
        }
      }
    }

    return false;
  }

  public int getNumberOfWorkingDays(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay)
  {
    synchronized (this)
    {
      Calendar startCal = new GregorianCalendar(startYear, startMonth, startDay);
      Calendar endCal = new GregorianCalendar(endYear, endMonth, endDay);

      int workDays = 0;

      if (startCal.getTimeInMillis() == endCal.getTimeInMillis())
      {
        return 0;
      }

      if (startCal.getTimeInMillis() > endCal.getTimeInMillis())
      {
        startCal = new GregorianCalendar(endYear, endMonth, endDay);
        endCal = new GregorianCalendar(startYear, startMonth, startDay);
      }
      int xxDay = 0;
      int yyDay = 0;

      do
      {
        if (isWorkingDay(startCal))
        {
          // System.out.println(startCal.get(Calendar.DAY_OF_MONTH));
          ++workDays;
        }
        startCal.add(Calendar.DAY_OF_YEAR, 1);
        xxDay = startCal.get(Calendar.DAY_OF_YEAR);
        yyDay = xxDay;
      } while (startCal.get(Calendar.DAY_OF_YEAR) <= endCal.get(Calendar.DAY_OF_YEAR)
          && startCal.get(Calendar.YEAR) <= endCal.get(Calendar.YEAR));

      return workDays;
    }
  }

  private List<String> readHolidays(String fileName)
  {
    List<String> myHolidays = new ArrayList<String>();

//    try
//    {
//      File file = new File(fileName);
//      List<String> lines = MyFileUtils.readLines(file);
//
//      for (String line : lines)
//      {
//        myHolidays.add(line);
//      }
//
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }

    return myHolidays;
  }

  public static void main(String[] args)
  {
    CalculateWorkingDays wd = new CalculateWorkingDays();

    // month starts at 0, day at 1
//    List<String> myHolidays = new ArrayList<String>();
//
//    myHolidays.add("24.12.2014");
//    myHolidays.add("25.12.2014");
//    myHolidays.add("26.12.2014");
//    myHolidays.add("31.12.2014");

    List<String> myHolidays = wd.readHolidays("C:/Workspaces/Tools/src/de/gecko/tools/holidays.txt");
    wd.initHolidays(myHolidays);

    System.out.println(wd.getNumberOfWorkingDays(2014, 11, 1, 2014, 11, 31));
    System.out.println(wd.getCurrentWorkingDay());

  }

}
