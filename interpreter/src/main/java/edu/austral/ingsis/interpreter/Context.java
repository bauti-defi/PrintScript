package edu.austral.ingsis.interpreter;

public class Context {

  private final DeclarationTable declarations = new DeclarationTable();

  public DeclarationTable getDeclarations() {
    return declarations;
  }
}
