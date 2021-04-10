package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.ReferenceAssignationNode;
import edu.austral.ingsis.ast.nodes.ReferenceNode;

import java.util.List;

public class ReferenceAssignationBuilder implements NodeBuilder<ReferenceAssignationNode> {

    private final ReferenceBuilder referenceBuilder = new ReferenceBuilder();
    private final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

    public boolean predicate(List<Token> tokens){
        return containsToken(tokens, TokenType.EQUALS) && (startsWith(tokens, TokenType.LET) || startsWith(tokens, TokenType.CONST));
    }

    public ReferenceAssignationNode build(List<Token> tokens){
        int index = getIndexOfToken(tokens, TokenType.EQUALS);

        final List<Token> declaration = tokens.subList(0, index);
        final List<Token> value = tokens.subList(index + 1, tokens.size());

        final ReferenceAssignationNode referenceAssignationNode = new ReferenceAssignationNode(tokens.get(index));

        if(referenceBuilder.predicate(declaration)){
            //Declare brand new variable
            final ReferenceNode node = referenceBuilder.build(declaration);
            referenceAssignationNode.setLeft(node);
        }

        //Get value of assignation
        referenceAssignationNode.setRight(expressionBuilder.build(value));

        return referenceAssignationNode;
    }
}
