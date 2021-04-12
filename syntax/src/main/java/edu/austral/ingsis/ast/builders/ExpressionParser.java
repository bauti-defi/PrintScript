package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.ExpressionNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExpressionParser implements NodeParser<ExpressionNode> {

    private final ReferenceParser referenceBuilder = new ReferenceParser();

    public boolean predicate(List<Token> tokens){
        return true;
    }

    public ExpressionNode parse(List<Token> tokens){
        final Stack<Token> postFix = ShuntingYard.process(tokens);
        return processShuntingYard(postFix);
    }

    //can return binary-op, declaration, or value-literal
    private ExpressionNode processShuntingYard(Stack<Token> postFix) {
        final Stack<ExpressionNode> nodes = new Stack<>();

        for (Token token : postFix) {
            if (isTokenType(token, TokenType.LITERAL)) {
                nodes.push(new ValueLiteralNode(token));
            } else if(isTokenType(token, TokenType.IDENTIFIER)){
                nodes.push(referenceBuilder.parse(Arrays.asList(token)));
            } else if (isTokenType(token, TokenType.STAR_SYMBOL) || isTokenType(token, TokenType.SLASH_SYMBOL) || isTokenType(token, TokenType.PLUS_SYMBOL) || isTokenType(token, TokenType.MINUS_SYMBOL)) {
                final BinaryOpNode operator = new BinaryOpNode(token);
                operator.setRight(nodes.pop());
                operator.setLeft(nodes.pop());

                nodes.push(operator);
            }
        }

        //The only node left in the stack should be the head
        return nodes.pop();
    }

}
