package edu.austral.ingsis.ast.exceptions;

public class VariableUndefinedException extends RuntimeException{

    public VariableUndefinedException(String identifier){
        super("Identifier " + identifier + " is undefined.");
    }
}
