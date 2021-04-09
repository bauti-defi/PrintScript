package edu.austral.ingsis.ast.builders;

import edu.austral.ingsis.ast.*;
import edu.austral.ingsis.ast.exceptions.SyntaxTokenExpectedException;
import edu.austral.ingsis.ast.exceptions.VariableUndefinedException;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;

import java.util.List;
import java.util.Stack;

public class ExpressionBuilder implements NodeBuilder<AbstractNode> {

    public boolean predicate(List<Token> tokens, DeclarationTable declarations){
        final Token unknownIdentifier = tokens.stream().filter(token -> isTokenType(token, TokenType.IDENTIFIER)).filter(token -> !declarations.contains(token.getValue())).findFirst().get();

        if(unknownIdentifier != null){
            throw new VariableUndefinedException(unknownIdentifier);
        }

        return true;
    }

    public AbstractNode build(List<Token> tokens, DeclarationTable declarations){
        final Stack<Token> postFix = ShuntingYard.process(tokens);
        return processShuntingYard(postFix, declarations);
    }

    //can return binary-op, declaration, or value-literal
    private AbstractNode processShuntingYard(Stack<Token> postFix, DeclarationTable declarations) {
        final Stack<AbstractNode> nodes = new Stack<>();

        for (Token token : postFix) {
            if (isTokenType(token, TokenType.NUMBER_LITERAL) || isTokenType(token, TokenType.STRING_LITERAL)) {
                nodes.push(new ValueLiteralNode(token));
            } else if(isTokenType(token, TokenType.IDENTIFIER)){
                nodes.push(declarations.get(token.getValue()));
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
