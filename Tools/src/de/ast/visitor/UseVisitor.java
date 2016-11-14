package de.ast.visitor;

import java.io.File;
import java.io.FileFilter;

public class UseVisitor
{
  static void traverseDirectory(File dir, DirectoryVisitor visitor)
  {
    visitor.enterDirectory(dir);
    File[] entries = dir.listFiles(
          new FileFilter()
          {
            public boolean accept(File pathname)
            {
              return true;
            }
          }
    );
    for(int i = 0; i < entries.length; i++)
    {
      if(entries[i].isDirectory())
      {
        traverseDirectory(entries[i], visitor);
      }
      else
      {
        visitor.visitFile(entries[i]);
      }
    }
    visitor.leaveDirectory(dir);
  }
  
  public static void main(String[] args)
  {
    DirectoryPrintVisitor visitor = new DirectoryPrintVisitor();
    UseVisitor.traverseDirectory(new File("C:/alex"), visitor);
    System.out.println("Directories : " + visitor.getDirectoryCount());
    System.out.println("Files       : " + visitor.getFileCount());
  }
}
