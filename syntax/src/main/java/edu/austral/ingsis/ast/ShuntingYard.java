package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.BinaryOpNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;
import edu.austral.ingsis.exceptions.SyntaxException;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.List;
import java.util.Stack;

public class ShuntingYard {


    private static boolean isTokenType(Token token, TokenType type){
        return token.getType() == type;
    }

    public static Stack<Token> process(List<Token> tokens){
        final Stack<Token> operatorStack = new Stack<>();
        final Stack<Token> expStack = new Stack<>();

        for(Token token: tokens){
            if(isTokenType(token, TokenType.L_PARENTHESES)){
                operatorStack.push(token);
            }else if(isTokenType(token, TokenType.NUMBER_LITERAL) || isTokenType(token, TokenType.STRING_LITERAL) || isTokenType(token, TokenType.IDENTIFIER)){
                expStack.push(token);
            }else if(isTokenType(token, TokenType.STAR_SYMBOL) || isTokenType(token, TokenType.SLASH_SYMBOL)){
                operatorStack.push(token);
            }else if(isTokenType(token, TokenType.PLUS_SYMBOL) || isTokenType(token, TokenType.MINUS_SYMBOL)){
                if(!operatorStack.empty()){
                    //Check precedence
                    while(isTokenType(operatorStack.peek(), TokenType.STAR_SYMBOL) || isTokenType(operatorStack.peek(), TokenType.SLASH_SYMBOL)){
                        Token top = operatorStack.pop();
                        expStack.push(top);
                    }
                }
                operatorStack.push(token);
            }else if(isTokenType(token, TokenType.R_PARANTHESES)){
                Token popped;
                while(!isTokenType((popped = operatorStack.pop()), TokenType.L_PARENTHESES)){
                    expStack.push(popped);
                }
            }else{
                throw new SyntaxException(token);
            }
        }

        expStack.addAll(operatorStack);

        return expStack;
    }

    public static Node evaluate(Stack<Token> postFix) {
        final Stack<Node> nodes = new Stack<>();

        for (Token token : postFix) {
            if (isTokenType(token, TokenType.NUMBER_LITERAL) || isTokenType(token, TokenType.STRING_LITERAL) || isTokenType(token, TokenType.IDENTIFIER)) {
                nodes.push(new ValueLiteralNode(token));
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
