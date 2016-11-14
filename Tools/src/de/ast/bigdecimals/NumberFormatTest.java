package de.ast.bigdecimals;

import static org.junit.Assert.*;

import java.math.*;
import java.text.*;
import java.util.*;

import org.junit.*;

public class NumberFormatTest
{

  @Test
  public void testNumberFormatParse() throws ParseException
  {
    String myNumber = "123456.789";
    
    String formatUK = "######,##0.###";
    String formatDE = "######,##0.###";
    
    DecimalFormat dfDE = new DecimalFormat(formatDE);
    DecimalFormat dfUK = new DecimalFormat(formatUK);

    Double dDE = dfDE.parse(myNumber).doubleValue();
    Double dUK = dfUK.parse(myNumber).doubleValue();
    
    System.out.println(dDE);
    System.out.println(dUK);
    
    System.out.println(dfDE.parse(myNumber).toString());
    System.out.println(dfUK.parse(myNumber).toString());
    
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(1);
    df.setMinimumFractionDigits(0);
    df.setGroupingUsed(false);
    
    Double d = df.parse(myNumber).doubleValue();
    System.out.println(d);
    
    
  }
  
  @Test
  public void testNumberFormatLocales()
  {
    Locale[] locales = NumberFormat.getAvailableLocales();
    double myNumber = -1234.56;
    
    NumberFormat form;
    for (int j = 0; j < 4; ++j)
    {
      System.out.println("FORMAT");
      for (int i = 0; i < locales.length; ++i)
      {
        if (locales[i].getCountry().length() == 0)
        {
          continue; // Skip language-only locales
        }
        System.out.print(locales[i].getDisplayName());
        switch (j)
        {
        case 0:
          form = NumberFormat.getInstance(locales[i]);
          break;
        case 1:
          form = NumberFormat.getIntegerInstance(locales[i]);
          break;
        case 2:
          form = NumberFormat.getCurrencyInstance(locales[i]);
          break;
        default:
          form = NumberFormat.getPercentInstance(locales[i]);
          break;
        }
        if (form instanceof DecimalFormat)
        {
          System.out.print(": " + ((DecimalFormat) form).toPattern());
        }
        System.out.print(" -> " + form.format(myNumber));
        try
        {
          System.out.println(" -> " + form.parse(form.format(myNumber)));
        }
        catch (ParseException e)
        {
        }
      }
    }
  }

}
