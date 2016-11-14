package de.ast.visitor;

import java.io.File;

public interface DirectoryVisitor
{
  public void enterDirectory(File dir);

  public void leaveDirectory(File dir);

  public void visitFile(File file);
}
