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
      throw new Exception("Variable " + identifier + " is undefined.");
    } else if(declarations.get(identifier).isImmutable() && values.containsKey(identifier)){
      throw new Exception("Variable " + identifier + " is immutable.");
    } else if(declarations.get(identifier).getType().equals("number") && !isNumeric(value)){
      throw new Exception("Variable " + identifier + " is of type number.");
    }else if(declarations.get(identifier).getType().equals("boolean") && !isBoolean(value)){
      throw new Exception("Variable " + identifier + " is of type boolean.");
    }
    values.put(identifier, value);
  }

  public String getValue(String identifier) throws Exception {
    if (isUndefined(identifier)) {
      if (parent != null) {
        return this.parent.getValue(identifier);
      }
      throw new Exception("Variable " + identifier + " is undefined.");
    }
    return values.get(identifier);
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }

  public static boolean isBoolean(String str) {
    try {
      Boolean.parseBoolean(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }

}
