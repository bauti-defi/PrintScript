package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.AssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.List;

public class AssignationBuilder implements NodeBuilder<AssignationNode> {

    private final DeclarationBuilder declarationBuilder = new DeclarationBuilder();
    private final ReferenceBuilder referenceBuilder = new ReferenceBuilder();
    private final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

    public boolean predicate(List<Token> tokens, DeclarationTable declarations){
        return containsToken(tokens, TokenType.EQUALS);
    }

    public AssignationNode build(List<Token> tokens, DeclarationTable declarations){
        int index = getIndexOfToken(tokens, TokenType.EQUALS);

        final List<Token> declaration = tokens.subList(0, index);
        final List<Token> value = tokens.subList(index + 1, tokens.size());

        final AssignationNode assignationNode = new AssignationNode(tokens.get(index));

        if(declarationBuilder.predicate(declaration, declarations)){
            //Declare brand new variable
            final DeclarationNode node = declarationBuilder.build(declaration, declarations);
            assignationNode.setLeft(node);
        }else if(referenceBuilder.predicate(declaration, declarations)){
            //using previous variable
            assignationNode.setLeft(referenceBuilder.build(declaration, declarations));
        }

        //Get value of assignation
        assignationNode.setRight(expressionBuilder.build(value, declarations));

        return assignationNode;
    }
}
