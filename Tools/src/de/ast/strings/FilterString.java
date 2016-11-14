package de.ast.strings;

import java.io.*;

import org.apache.commons.io.*;

public class FilterString
{

  public FilterString()
  {
  }

  private String filterFor(String origin)
  {
    String filteredString = null;

    char array[] = origin.toCharArray();

    for (int i = 0; i < array.length; i++)
    {
      int nVal = (int) array[i];
      boolean bISO = Character.isISOControl(array[i]);
      boolean bIgnorable = Character.isIdentifierIgnorable(array[i]);
      // Remove tab and other unwanted characters..
      if (nVal == 9 || bISO || bIgnorable)
      {
        array[i] = ' ';
      }
      else if (nVal > 255)
      {
        array[i] = ' ';
      }
    }
    filteredString = new String(array);

    return filteredString;
  }


  public static void main(String[] args) throws Exception
  {
    FilterString filterString = new FilterString();
    File file = new File("c:/Temp/filterFile.txt");
    
    String fileContent = FileUtils.readFileToString(file, "UTF-8");
    
    String filteredContent = filterString.filterFor(fileContent);
    
    File destFile = new File(file.getCanonicalPath() + ".filtered");
    FileUtils.writeStringToFile(destFile, filteredContent, "UTF-8");

  }

}
