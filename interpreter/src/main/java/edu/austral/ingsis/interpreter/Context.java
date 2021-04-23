package edu.austral.ingsis.interpreter;

public class Context {

  private final VariableTable variables = new VariableTable();

  public VariableTable getVariables() {
    return variables;
  }
}
