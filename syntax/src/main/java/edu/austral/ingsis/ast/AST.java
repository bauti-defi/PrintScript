package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.AbstractNode;

import java.util.List;

public class AST {

    private final DeclarationTable declarations;
    private final List<AbstractNode> nodes;

    private AST(ASTBuilder builder) {
        this.nodes = builder.nodes;
        this.declarations = builder.declarations;
    }

    public DeclarationTable getDeclarations() {
        return declarations;
    }

    public List<AbstractNode> getNodes() {
        return nodes;
    }

    public static AST create(List<Token> tokens){
        final ASTBuilder builder = new ASTBuilder();
        builder.process(tokens);
        return new AST(builder);
    }
}
