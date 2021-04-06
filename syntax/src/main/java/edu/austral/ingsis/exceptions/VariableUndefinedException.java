package edu.austral.ingsis.exceptions;

public class VariableUndefinedException extends RuntimeException{

    public VariableUndefinedException(String identifier){
        super("Identifier " + identifier + " is undefined.");
    }
}
