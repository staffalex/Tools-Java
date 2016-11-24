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
    
    System.out.println("Hostname of VBox host : " + InetAddress.getByName("W81LSTAF007.asysoffice.de").getHostName());
    System.out.println("IsLoopBackAddress (W81LSTAF007) : " + InetAddress.getByName("W81LSTAF007.asysoffice.de").isLoopbackAddress());
    
    System.out.println("LoopBackAddress : " + InetAddress.getLocalHost());
    System.out.println("IsLoopBackAddress : " + InetAddress.getLocalHost().isLoopbackAddress());
    
    System.out.println("GetCanonicalHostname : " + InetAddress.getLocalHost().getCanonicalHostName());
    
    System.out.println("Get gecko-dev by hostname : " + InetAddress.getByName("XUbuntuV16").getHostName());
    System.out.println("GetCanonicalHostname : " + InetAddress.getLocalHost().getCanonicalHostName());
  }

}
