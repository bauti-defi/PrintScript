package edu.austral.ingsis.interpreter;

public class Declaration {

  private final String identifier, type;
  private final boolean immutable;

  public Declaration(String identifier, boolean immutable, String type) {
    this.identifier = identifier;
    this.immutable = immutable;
    this.type = type;
  }

  public String getIdentifier() {
    return identifier;
  }

  public boolean isImmutable() {
    return immutable;
  }

  public String getType() {
    return type;
  }
}
