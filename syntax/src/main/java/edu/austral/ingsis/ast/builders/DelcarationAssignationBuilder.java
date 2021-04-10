package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.List;

public class DelcarationAssignationBuilder implements NodeBuilder<DeclarationAssignationNode> {

    private final DeclarationBuilder declarationBuilder = new DeclarationBuilder();
    private final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

    public boolean predicate(List<Token> tokens){
        return containsToken(tokens, TokenType.EQUALS) && (startsWith(tokens, TokenType.LET) || startsWith(tokens, TokenType.CONST));
    }

    public DeclarationAssignationNode build(List<Token> tokens){
        int index = getIndexOfToken(tokens, TokenType.EQUALS);

        final List<Token> declaration = tokens.subList(0, index);
        final List<Token> value = tokens.subList(index + 1, tokens.size());

        final DeclarationAssignationNode declarationAssignationNode = new DeclarationAssignationNode(tokens.get(index));

        if(declarationBuilder.predicate(declaration)){
            //Declare brand new variable
            final DeclarationNode node = declarationBuilder.build(declaration);
            declarationAssignationNode.setLeft(node);
        }

        //Get value of assignation
        declarationAssignationNode.setRight(expressionBuilder.build(value));

        return declarationAssignationNode;
    }
}
