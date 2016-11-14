package de.ast.file;

import java.io.*;

import org.apache.commons.io.*;

public class MyFileUtils
{
  public String baseDir = "C:/work/git/XXX/ims-ng/work";
  public String importDir = baseDir + "/import";
  public String groupingDir = baseDir + "/grouping";

  public void deleteDirs() throws IOException
  {
    File importFile = new File(importDir);
    File groupingFile = new File(groupingDir);

    FileUtils.deleteDirectory(importFile);
    FileUtils.deleteDirectory(groupingFile);
  }

  public void createDirs() throws IOException
  {
    File importFile = new File(importDir);
    File groupingFile = new File(groupingDir);

    FileUtils.forceMkdir(importFile);
    FileUtils.forceMkdir(groupingFile);
  }

  public static void main(String[] args)
  {
    MyFileUtils mfu = new MyFileUtils();

    try
    {
      mfu.deleteDirs();
      mfu.createDirs();

      File importFile = new File(mfu.importDir);
      File groupingFile = new File(mfu.groupingDir);

      System.out.println("Directory " + mfu.importDir + " created ? : " + importFile.isDirectory());
      System.out.println("Directory " + mfu.groupingDir + " created ? : " + groupingFile.isDirectory());
    }
    catch (IOException ioex)
    {
      System.out.println("Error accessing directories : " + ioex.getMessage());
    }
  }

}
