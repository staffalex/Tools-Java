package de.ast.compareTo;

public class CompareToClass
{

  private int iVal;
  private double dVal;
  private String sVal;

  public int getiVal()
  {
    return iVal;
  }

  public void setiVal(int iVal)
  {
    this.iVal = iVal;
  }

  public double getdVal()
  {
    return dVal;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    
    if (obj == null)
    {
      return false;
    }
    
    if (getClass() != obj.getClass())
    {
      return false;
    }
    
    CompareToClass other = (CompareToClass) obj;
    if (Double.doubleToLongBits(dVal) != Double.doubleToLongBits(other.dVal))
    {
      return false;
    }
    
    if (iVal != other.iVal)
    {
      return false;
    }
    
    if (sVal == null)
    {
      if (other.sVal != null)
      {
        return false;
      }
    }
    else if (!sVal.equals(other.sVal))
    {
      return false;
    }
    return true;
  }

  public void setdVal(double dVal)
  {
    this.dVal = dVal;
  }

  public String getsVal()
  {
    return sVal;
  }

  public void setsVal(String sVal)
  {
    this.sVal = sVal;
  }

  public static void main(String[] args)
  {
    CompareToClass cc1 = new CompareToClass();
    cc1.setiVal(4);
    cc1.setdVal(4.0);
    cc1.setsVal("44");

    CompareToClass cc2 = new CompareToClass()
    {
      @Override
      public boolean equals(Object obj)
      {
        if (this == obj)
        {
          return true;
        }
        if (obj == null)
        {
          return false;
        }
        if (getClass() != obj.getClass())
        {
          return false;
        }
        CompareToClass other = (CompareToClass) obj;
        if (Double.doubleToLongBits(this.getdVal()) != Double.doubleToLongBits(other.dVal))
        {
          return false;
        }
        if (this.getiVal() != other.iVal)
        {
          return false;
        }
        if (getsVal() == null)
        {
          if (other.sVal != null)
          {
            return false;
          }
        }
        else if (!getsVal().equals(other.sVal))
        {
          return false;
        }
        return true;
      }
    };

  }
}
