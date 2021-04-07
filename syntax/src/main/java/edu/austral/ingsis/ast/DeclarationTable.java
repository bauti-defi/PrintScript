package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.exceptions.SyntaxException;
import edu.austral.ingsis.exceptions.VariableUndefinedException;

import java.util.HashMap;

public class DeclarationTable {

    private final HashMap<String, DeclarationNode> declarations = new HashMap<>();

    public DeclarationNode get(String identifier){
        if(!declarations.containsKey(identifier)){
            throw new VariableUndefinedException(identifier);
        }

        return declarations.get(identifier);
    }

    public DeclarationNode put(String identifier, DeclarationNode declaration){
        return declarations.put(identifier, declaration);
    }
}

