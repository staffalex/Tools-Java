package de.ast.enums;

public class EnumsTest
{

  public static void main(String[] args)
  {
    
    String enumName = "COMMISSION_INVOICE_GROUPXX";
    
    if (DocumentType.findByName(enumName) != null)
    {
      System.out.println("Found!");
    }
    else
    {
      System.out.println("Not Found!");
    }
    
    System.out.println(DocumentType.CREDIT_NOTE);

  }

}
