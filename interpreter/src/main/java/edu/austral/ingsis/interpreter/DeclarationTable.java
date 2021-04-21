package edu.austral.ingsis.interpreter;

import java.util.HashMap;

public class DeclarationTable {

  private final HashMap<String, Declaration> declarations = new HashMap<>(); // value, immutable

  public void insert(String identifier, String type, boolean immutable) throws Exception {
    if (declarations.containsKey(identifier) && declarations.get(identifier).isImmutable()) {
      throw new Exception("Can't re-assign immutable variable.");
    }
    declarations.put(identifier, new Declaration(identifier, immutable, type));
  }

  public Declaration get(String identifier) {
    return declarations.get(identifier);
  }

  public boolean contains(String identifier) {
    return declarations.containsKey(identifier);
  }
}
