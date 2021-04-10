package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;

import java.util.List;

public class DelcarationAssignationParser implements NodeParser<DeclarationAssignationNode> {

    private final DeclarationParser declarationParser = new DeclarationParser();
    private final ExpressionParser expressionParser = new ExpressionParser();

    public boolean predicate(List<Token> tokens){
        return containsToken(tokens, TokenType.EQUALS) && (startsWith(tokens, TokenType.LET) || startsWith(tokens, TokenType.CONST));
    }

    public DeclarationAssignationNode parse(List<Token> tokens){
        int index = getIndexOfToken(tokens, TokenType.EQUALS);

        final List<Token> declaration = tokens.subList(0, index);
        final List<Token> value = tokens.subList(index + 1, tokens.size());

        final DeclarationAssignationNode declarationAssignationNode = new DeclarationAssignationNode(tokens.get(index));

        if(declarationParser.predicate(declaration)){
            //Declare brand new variable
            final DeclarationNode node = declarationParser.parse(declaration);
            declarationAssignationNode.setLeft(node);
        }

        //Get value of assignation
        declarationAssignationNode.setRight(expressionParser.parse(value));

        return declarationAssignationNode;
    }
}
