package de.ast.DecimalFormat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

public class DecimalFormatTest
{

  @Test
  public void testLocaleNumberFormats()
  {
    Comparator<Locale> comp = new LocaleDisplayNameComparator();
    Locale[] locales = NumberFormat.getAvailableLocales();
    Arrays.sort(locales,  comp);
    
    NumberFormat form;
    
    Set<String> formatsSet = new HashSet<String>();
    
      System.out.println("FORMATS");
      for (int i = 0; i < locales.length; ++i)
      {
        if (locales[i].getCountry().length() == 0)
        {
          continue; // Skip language-only locales
        }
        
        form = NumberFormat.getInstance(locales[i]);
        formatsSet.add(((DecimalFormat) form).toPattern());
        
      }
      
      for(String format : formatsSet)
      {
        System.out.println(format);
      }
  }

  @Test
  public void printNumberFormatLocales()
  {
    Comparator<Locale> comp = new LocaleDisplayNameComparator();
    Locale[] locales = NumberFormat.getAvailableLocales();
    Arrays.sort(locales,  comp);
    
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
  
  @Test
  public void LocalizedNumberFormatTestHowItWorks() throws Exception
  {
    double myNumber = -1234.56;
    
    printNumber(Locale.GERMAN, myNumber);
    printNumber(Locale.ENGLISH, myNumber);
    printNumber(Locale.FRENCH, myNumber);
    printNumber(Locale.CHINESE, myNumber);
  }
  
  private void printNumber(Locale locale, double myNumber) throws Exception
  {
    Locale.setDefault(locale);
    DecimalFormat df = new DecimalFormat("##0.#");

    StringBuffer sb = new StringBuffer();
    
    sb.append(df.toPattern()).append(" : ").append(df.format(myNumber)).append(" : ").append(df.parse(df.format(myNumber)));
    System.out.println(sb);
  }
 
  @Test
  public void LocalizedNumberFormatTestHowItNotWorks() throws Exception
  {
    DecimalFormat df = new DecimalFormat("##0.#");
    double myNumber = -1234.56;
    
    printNumber(Locale.GERMAN, df, myNumber);
    printNumber(Locale.ENGLISH, df, myNumber);
    printNumber(Locale.FRENCH, df, myNumber);
    printNumber(Locale.CHINESE, df, myNumber);
  }
  
  private void printNumber(Locale locale, DecimalFormat df, double myNumber) throws Exception
  {
    Locale.setDefault(locale);

    StringBuffer sb = new StringBuffer();
    
    sb.append(df.toPattern()).append(" : ").append(df.format(myNumber)).append(" : ").append(df.parse(df.format(myNumber)));
    System.out.println(sb);
  }
}
