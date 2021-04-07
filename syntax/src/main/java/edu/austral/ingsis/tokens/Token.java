package edu.austral.ingsis.tokens;

public interface Token {

    String getValue();
    Integer getLineNumber();
    Integer getLineIndex();
    TokenType getType();

    default String asString(){
        return getValue() + " of type " + getType().toString() + " at line " + getLineNumber() + " and line index " + getLineIndex();
    }
}
