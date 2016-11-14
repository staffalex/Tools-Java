package de.ast.Collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashCodeTest
{

  @Test
  public void testStringHashCode1()
  {
    String s1 = "abc123";
    String s2 = "abc123";
    String s3 = "abc123";
    String s4 = "abc123";
    
    System.out.println(s1 + " : " + s1.hashCode());
    System.out.println(s2 + " : " + s2.hashCode());
    System.out.println(s3 + " : " + s3.hashCode());
    System.out.println(s4 + " : " + s4.hashCode());
    
  }

  @Test
  public void testStringHashCode2()
  {
    StringWithHashCode swh1 = new StringWithHashCode("abc123");
    StringWithHashCode swh2 = new StringWithHashCode("abc123");
    StringWithHashCode swh3 = new StringWithHashCode("abc123");
    StringWithHashCode swh4 = new StringWithHashCode("abc123");
  
    System.out.println(swh1);
    System.out.println(swh2);
    System.out.println(swh3);
    System.out.println(swh4);
    
  }
}
