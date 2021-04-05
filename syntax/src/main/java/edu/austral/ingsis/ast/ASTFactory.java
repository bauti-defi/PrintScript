package edu.austral.ingsis.ast;

import edu.austral.ingsis.SyntaxTable;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.exceptions.SemicolonAbsentException;
import edu.austral.ingsis.exceptions.SyntaxException;
import edu.austral.ingsis.exceptions.SyntaxTokenExpectedException;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class ASTFactory {


    public void create(List<Token> tokens){
        final SyntaxTable table = new SyntaxTable(tokens);

        int lineCount = tokens.get(tokens.size() - 1).getLineNumber();
        final Map<Integer, List<Token>> lines = tokens.stream().collect(Collectors.groupingBy(Token::getLineNumber));

        for(int i = 0;i < lineCount;i++){
            final List<Token> line = lines.get(i);
            if(!endsWithSemicolon(line)){
                throw new SemicolonAbsentException(line.get(line.size() - 1));
            }

            //process without semicolon
            processLine(line.subList(0, line.size() - 1), table);
        }
    }

    private boolean isTokenType(Token token, TokenType type){
        return token.getType() == type;
    }

    private boolean containsToken(List<Token> token, TokenType condition){
        return token.stream().anyMatch(t -> t.getType() == condition);
    }

    private boolean startsWith(List<Token> tokens, TokenType type){
        return tokens.get(0).getType() == type;
    }

    private boolean endsWith(List<Token> tokens, TokenType type){
        return tokens.get(tokens.size() - 1).getType() == type;
    }

    private boolean endsWithSemicolon(List<Token> tokens){
        return endsWith(tokens, TokenType.SEMICOLON);
    }

    public Node processLine(List<Token> line, SyntaxTable table){
        if(containsToken(line, TokenType.EQUALS)){
            return processAssignation(line, table);
        }

        return null;
    }

    private int getIndexOfToken(List<Token> line, TokenType type){
        int index;
        for(index = 0; index < line.size(); index++){
            if(line.get(index).getType() == type){
                return index;
            }
        }
        return -1;
    }

    private AssignationNode processAssignation(List<Token> line, SyntaxTable table){
        int index = getIndexOfToken(line, TokenType.EQUALS);

        final List<Token> declaration = line.subList(0, index);
        final List<Token> value = line.subList(index + 1, line.size() - 1);

        final AssignationNode node = new AssignationNode(line.get(index));

        if(startsWith(declaration, TokenType.LET)){
            node.setLeft(processDeclaration(declaration));
        }else{
            //using previous variable
        }
        node.setRight(processValue(value));

        return node;
    }

    private DeclarationNode processDeclaration(List<Token> tokens){
        int colonIndex = getIndexOfToken(tokens, TokenType.COLON);

        //Check that there is a colon
        if(colonIndex == -1){
            throw new SyntaxTokenExpectedException(tokens.get(0).getLineNumber(), TokenType.COLON);
        }

        //check that we have an identifier
        if(tokens.get(colonIndex - 1).getType() != TokenType.IDENTIFIER){
            throw new SyntaxTokenExpectedException(tokens.get(colonIndex - 1), TokenType.IDENTIFIER);
        }

        //Check we have a type
        if(tokens.get(colonIndex + 1).getType() != TokenType.STRING_TYPE && tokens.get(colonIndex + 1).getType() != TokenType.NUMBER_TYPE) {
            throw new SyntaxTokenExpectedException(tokens.get(colonIndex + 1), TokenType.NUMBER_TYPE);
        }


        final DeclarationNode node = new DeclarationNode(tokens.get(0));
        node.setLeft(new IdentifierNode(tokens.get(colonIndex - 1)));
        node.setRight(new TypeNode(tokens.get(colonIndex + 1)));

        return node;
    }

    private ValueLiteralNode processValue(List<Token> tokens){
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
                //Check precedence
                while(isTokenType(operatorStack.peek(), TokenType.STAR_SYMBOL) || isTokenType(operatorStack.peek(), TokenType.SLASH_SYMBOL)){
                    Token top = operatorStack.pop();
                    expStack.push(top);
                }
                operatorStack.push(token);
            }else if(isTokenType(token, TokenType.R_PARANTHESES)){
                Token popped;
                while(!isTokenType((popped = operatorStack.pop()), TokenType.L_PARENTHESES)){
                    expStack.push(popped);
                }
                operatorStack.pop();
            }else{
                throw new SyntaxException(token);
            }
        }


        return null;
    }



}
