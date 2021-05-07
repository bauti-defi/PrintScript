package edu.austral.ingsis.interpreter;

import java.util.HashMap;

public class Context {

  private final HashMap<String, Declaration> declarations =
      new HashMap<>(); // identifier, type, immutable
  private final HashMap<String, String> values = new HashMap<>(); // value

  private Context parent;

  public Context(Context parent) {
    this.parent = parent;
  }

  public Context() {
    this(null);
  }

  public void insertDeclaration(Declaration declaration) throws Exception {
    declarations.put(declaration.getIdentifier(), declaration);
  }

  public Declaration getDeclaration(String identifier) {
    return declarations.get(identifier);
  }

  public boolean isUndefined(String identifier) {
    return !declarations.containsKey(identifier);
  }

  public void setValue(String identifier, String value) throws Exception {
    if (isUndefined(identifier)) {
      if (parent != null) {
        this.parent.setValue(identifier, value);
        return;
      }
      throw new Exception("Variable" + identifier + " is undefined.");
    }
    values.put(identifier, value);
  }

  public String getValue(String identifier) throws Exception {
    if (isUndefined(identifier)) {
      if (parent != null) {
        return this.parent.getValue(identifier);
      }
      throw new Exception("Variable" + identifier + " is undefined.");
    }
    return values.get(identifier);
  }

  public boolean isNull(String identifier) {
    return !values.containsKey(identifier);
  }
}
