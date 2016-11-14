package de.ast.visitor;

import java.io.File;

public class DirectoryPrintVisitor implements DirectoryVisitor
{

  String indent = "";
  int indentLength = 2;
  
  int directoryCount = 0;
  public int getDirectoryCount()
  {
    return directoryCount;
  }

  public int getFileCount()
  {
    return fileCount;
  }

  int fileCount = 0;
  
  @Override
  public void enterDirectory(File dir)
  {
    System.out.println(indent + "[" + dir.getName() + "]");
    indent += "  ";
    directoryCount++;
  }

  @Override
  public void leaveDirectory(File dir)
  {
    System.out.println(indent + "[" + dir.getName() + "]");
    indent = indent.substring(indentLength);
  }

  @Override
  public void visitFile(File file)
  {
    System.out.println(indent + file.getName());
    fileCount++;
  }
  
}
