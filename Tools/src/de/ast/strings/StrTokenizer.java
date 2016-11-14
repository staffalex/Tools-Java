package de.ast.strings;

public class StrTokenizer
{
  
  String str= "abc   def   ghi     \t jkl   ";

  public void dumpTokenizedStr()
  {
    String[] tokens = str.split("\\s+");
    for(int i = 0; i < tokens.length; i++)
    {
      System.out.println("#" + tokens[i] + "#");
    }
  }
  
  public void concatenateTokens()
  {
    String[] tokens = str.split("\\s+");
    StringBuffer normalizedString = new StringBuffer();
    
    for(int i = 0; i < tokens.length; i++)
    {
      if(i > 0)
      {
        normalizedString.append(" ");
      }
      normalizedString.append(tokens[i]);
    }
    
    System.out.println("#" + normalizedString.toString() + "#");
    
  }
  
  public void dumpNormalizedString()
  {
    String normalizedString = str.replaceAll("\\s+", " ").trim();
    System.out.println("#" + normalizedString + "#");
  }
  
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    StrTokenizer st = new StrTokenizer();
    st.dumpTokenizedStr();
    
    st.dumpNormalizedString();
    
    st.concatenateTokens();
  }

}
