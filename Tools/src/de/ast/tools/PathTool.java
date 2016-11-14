package de.ast.tools;

public class PathTool
{

  // Erst haenge ich alles zusammen
  // Dann ersetze ich alle mehrfach vorkommenden '/' durch einfache '/'
  public String createURL(String... args)
  {
    String path = "";
    String shortPath;
    String selectedProt = "";

    String[] protocols = { "http://", "https://", "ftp://", "sftp://" };

    for (String arg : args)
    {
      path += arg + '/';
      System.out.println(arg);
    }

    shortPath = path;
    for (String prot : protocols)
    {
      if (path.startsWith(prot))
      {
        selectedProt = prot;
        shortPath = path.substring(prot.length() + 1);
      }
    }

    path = selectedProt + shortPath.replace("/+", "/");

    return path;
  }

  public static void main(String[] args)
  {

    PathTool pt = new PathTool();

    System.out.println(pt.createURL("http://", "abc", "def", "ghi", "jkl", "mno", "pqr", "/stu/", "vwx", "yz"));

  }

}
