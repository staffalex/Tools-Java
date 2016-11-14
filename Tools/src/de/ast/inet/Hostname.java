package de.ast.inet;

import java.net.*;

public class Hostname
{

  /**
   * @param args
   * @throws UnknownHostException 
   */
  public static void main(String[] args) throws UnknownHostException
  {
    System.out.println("Hostname : " + InetAddress.getLocalHost().getHostName());
    System.out.println("IsLoopBackAddress : " + InetAddress.getLocalHost().isLoopbackAddress());
    System.out.println("LoopBackAddress : " + InetAddress.getLoopbackAddress().getLocalHost());
    System.out.println("IsLoopBackAddress : " + InetAddress.getLocalHost().isLoopbackAddress());
    
    System.out.println("GetCanonicalHostname : " + InetAddress.getLocalHost().getCanonicalHostName());
    
    System.out.println("Get gecko-dev by hostname : " + InetAddress.getByName("gecko-dev").getHostName());
    System.out.println("GetCanonicalHostname : " + InetAddress.getLocalHost().getCanonicalHostName());
  }

}
