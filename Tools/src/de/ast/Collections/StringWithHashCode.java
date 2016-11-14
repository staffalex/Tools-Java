package de.ast.Collections;

public class StringWithHashCode
{
  private String s = "abc123";

  public StringWithHashCode()
  {
  }

  public StringWithHashCode(String s)
  {
    this.s = s;
  }

  public String getS()
  {
    return s;
  }

  public void setS(String s)
  {
    this.s = s;
  }

  @Override
  public String toString()
  {
    return "StringWithHashCode [s=" + s + "][" + hashCode() + "]";
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((s == null) ? 0 : s.hashCode());
    return result;
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

    StringWithHashCode other = (StringWithHashCode) obj;
    if (s == null)
    {
      if (other.s != null)
      {
        return false;
      }
    }
    else if (!s.equals(other.s))
    {
      return false;
    }
    
    return true;
  }

}
