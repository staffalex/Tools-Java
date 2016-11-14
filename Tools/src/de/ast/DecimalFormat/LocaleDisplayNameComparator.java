package de.ast.DecimalFormat;

import java.util.Comparator;
import java.util.Locale;

public class LocaleDisplayNameComparator implements Comparator<Locale>
{
  @Override
  public int compare(Locale l1, Locale l2)
  {
    if(l1.getDisplayName() == null && l2.getDisplayName() == null)
    {
      return 0;
    }
    if(l1.getDisplayName() == null)
    {
      return 1;
    }
    if(l2.getDisplayName() == null)
    {
      return -1;
    }
    return l1.getDisplayName().compareTo(l2.getDisplayName());
  }
}
