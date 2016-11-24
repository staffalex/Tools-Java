package de.astaff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloLog4JWithMaven {

  final static Logger logger = LogManager.getLogger(HelloLog4JWithMaven.class);
  
  private void writeToLog(String message) {
    logger.debug(message);
  }

  public static void main(String[] args) {
    HelloLog4JWithMaven hello = new HelloLog4JWithMaven();
    hello.writeToLog("My log text");

  }

}
