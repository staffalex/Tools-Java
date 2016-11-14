package de.ast.enums;

public enum DocumentType
{
  INVOICE("INV", null), 
  CREDIT_NOTE("CN", INVOICE), 
  COMMISSION_CREDIT_NOTE("CCN", null), 
  COMMISSION_INVOICE("CINV",
  COMMISSION_CREDIT_NOTE), 
  CONFIRMATION("CL", null),

  INVOICE_GROUP("INVG", null), 
  COMMISSION_INVOICE_GROUP("CINVG", null), 
  RESEND_REQUEST("RSREQ", null),

  ONBOARD_INVOICE("OBINV", null), 
  ONBOARD_INVOICE_GIFTCARD("OBINV_GC", null);

  private final String name;
  private final DocumentType reverse;

  private DocumentType(String name, DocumentType reverse)
  {
    this.name = name;
    this.reverse = reverse;
  }

  public String getName()
  {
    return name;
  }

  public DocumentType getReverse()
  {
    return reverse;
  }

  public static DocumentType findByName(String name)
  {
    for (DocumentType v : values())
    {
      if (v.name().equals(name))
      {
        return v;
      }
    }
    return null;
  }

}
