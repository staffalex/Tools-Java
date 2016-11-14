package de.ast.file;

import java.io.*;

import org.apache.commons.io.*;

public class Filename
{
  public Filename()
  {
  }
  
  static void printFilenames(String filename) throws Exception
  {
    File file = new File(filename);
    
    System.out.println("fileName      : " + filename);
    System.out.println("AbsolutePath  : " + file.getAbsolutePath());
    System.out.println("CanonicalPath : " + file.getCanonicalPath());
    System.out.println("Name          : " + file.getName());
    System.out.println("Parent        : " + file.getParent());
    System.out.println("Path          : " + file.getPath());
    System.out.println("AbsoluteFile  : " + file.getAbsoluteFile());
    System.out.println("");
  }
  
  static void printFilenamesWithUtils(String filename) throws Exception
  {
    System.out.println("filename : " + filename);
    System.out.println("getBaseName :                " + FilenameUtils.getBaseName(filename));
    System.out.println("getExtension :               " + FilenameUtils.getExtension(filename));
    System.out.println("getFullPath :                " + FilenameUtils.getFullPath(filename));
    System.out.println("getFullPathNoEndSeparator :  " + FilenameUtils.getFullPathNoEndSeparator( filename));
    System.out.println("getName :                    " + FilenameUtils.getName(filename));
    System.out.println("getPath :                    " + FilenameUtils.getPath(filename));
    System.out.println("getPathNoEndSeparator :      " + FilenameUtils.getPathNoEndSeparator(filename));
    System.out.println("getPrefix :                  " + FilenameUtils.getPrefix(filename));
    System.out.println("getPrefixLength :            " + FilenameUtils.getPrefixLength(filename));
    System.out.println(" ");
  }

  public static void main(String[] args) throws Exception
  {
    Filename.printFilenamesWithUtils("filename.txt");
    Filename.printFilenamesWithUtils("c:/work/filename.txt");
    Filename.printFilenamesWithUtils("../filename.txt");
    Filename.printFilenamesWithUtils("c:/work/java/../filename.txt");
    Filename.printFilenamesWithUtils("2015-11-26-excluded-bookings.properties");
    
    System.out.println("");
  }

}
