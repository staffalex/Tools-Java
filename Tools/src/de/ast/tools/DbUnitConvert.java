package de.ast.tools;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.text.*;

public class DbUnitConvert
{

  public List<String> getColumns(String line, boolean cut)
  {
    StrTokenizer st = new StrTokenizer(line,"\t");
    st.setIgnoreEmptyTokens(false);
    st.setEmptyTokenAsNull(false);
    
    if (cut)
    {
      List<String> labels = getCutLabels(st.getTokenList());
      return labels;
    }
    else
    {
      List<String> labels = getWrappedValues(st.getTokenList());
      return labels;
    }
  }


  private List<String> getWrappedValues(List<String> tokenList)
  {
    List<String> labels = new ArrayList<String>();
    
    for(String label : tokenList)
    {
      if (!label.startsWith("\""))
      {
        labels.add("'" + label + "'");
      }
      else
      {
        labels.add(label);
      }
    }
    
    return labels;
  }


  private List<String> getCutLabels(List<String> tokenList)
  {
    List<String> labels = new ArrayList<String>();
    
    for(String label : tokenList)
    {
      labels.add(label.replace("\"", ""));
    }
    
    return labels;
  }


  public void createDbUnitXMLFile(File sourceFile, File destFile) throws Exception
  {

    BufferedReader br = null;
    List<String> labels = null;
    List<String> values = null;
    List<String> combinedLines = new ArrayList<String>();
    
    br = new BufferedReader(new FileReader(sourceFile));
    
    int index = 0;
    for (String line; (line = br.readLine()) != null;)
    {
      // process the line.
      if (index == 0)
      {
        labels = getColumns(line, true);
      }
      else
      {
        values = getColumns(line, false);
        combinedLines.add(createCombinedLine(labels, values));
      }
      index++;
      
    }
    br.close();
    
    for(String combinedLine : combinedLines)
    {
      System.out.println(combinedLine);
    }
    
    
    createXMLFile(destFile, combinedLines);
    
  }

  private String createCombinedLine(List<String> labels, List<String> values)
  {
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < labels.size(); i++)
    {
      sb.append(labels.get(i)).append("=").append(values.get(i)).append(" ");
    }
    
    return sb.toString();
  }


  private void createXMLFile(File destFile, List<String> lines) throws Exception
  {
    String header = "<?xml version='1.0' encoding='UTF-8'?>\n<dataset>\n";
    String footer = "</dataset>";
    String prefix = "<AWS_INVOICE_GC_V ";
    String postfix = " />";
    
    BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));
    
    bw.write(header);
    
    for(String line : lines)
    {
      bw.write(prefix);
      bw.write(line);
      bw.write(postfix);
      bw.write("\n");
    }
    
    bw.write(footer);
    
    bw.close();
  }


  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    DbUnitConvert dbUnitConvert = new DbUnitConvert();
    
    String path = "c:/work/Projekte/IBE3/db-dumps/";
    
    File sourceFile = new File(path + "typo-3479.txt");
    File destFile = new File(path + "typo-3479-out.xml");

    
    try
    {
      dbUnitConvert.createDbUnitXMLFile(sourceFile, destFile);
    }
    catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

}
