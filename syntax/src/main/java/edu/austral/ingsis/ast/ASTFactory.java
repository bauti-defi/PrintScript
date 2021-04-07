package edu.austral.ingsis.ast;

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

    private final DeclarationTable declarations = new DeclarationTable();

    public void create(List<Token> tokens){
        int lineCount = tokens.get(tokens.size() - 1).getLineNumber();
        final Map<Integer, List<Token>> lines = tokens.stream().collect(Collectors.groupingBy(Token::getLineNumber));

        for(int i = 0; i < lineCount; i++){
            final List<Token> line = lines.get(i);
            if(!endsWithSemicolon(line)){
                throw new SemicolonAbsentException(line.get(line.size() - 1));
            }

            //process without semicolon
            processStatement(line.subList(0, line.size() - 1));
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

    private AbstractNode processStatement(List<Token> line){
        if(containsToken(line, TokenType.EQUALS)){
            return processAssignation(line);
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

    private AssignationNode processAssignation(List<Token> line){
        int index = getIndexOfToken(line, TokenType.EQUALS);

        final List<Token> declaration = line.subList(0, index);
        final List<Token> value = line.subList(index + 1, line.size() - 1);

        final AssignationNode assignationNode = new AssignationNode(line.get(index));

        if(startsWith(declaration, TokenType.LET)){
            //Declare brand new variable
            final DeclarationNode node = processNewDeclaration(declaration);
            assignationNode.setLeft(node);
            declarations.put(node.getLeft().getToken().getValue(), node);
        }else{
            //using previous variable
            assignationNode.setLeft(processDeclaration(declaration));
        }

        //Get value of assignation
        assignationNode.setRight(processValue(value));

        return assignationNode;
    }

    //can return binary-op, declaration, or value-literal
    private AbstractNode processShuntingYard(Stack<Token> postFix) {
        final Stack<AbstractNode> nodes = new Stack<>();


        for (Token token : postFix) {
            if (isTokenType(token, TokenType.NUMBER_LITERAL) || isTokenType(token, TokenType.STRING_LITERAL)) {
                nodes.push(new ValueLiteralNode(token));
            } else if(isTokenType(token, TokenType.IDENTIFIER)){
                nodes.push(new IdentifierNode(token));
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

    private DeclarationNode processDeclaration(List<Token> tokens){
        if(!startsWith(tokens, TokenType.IDENTIFIER)){
            throw new SyntaxTokenExpectedException(tokens.get(0), TokenType.IDENTIFIER);
        }

        final String identifier = tokens.get(0).getValue();

        return declarations.get(identifier);
    }

    private DeclarationNode processNewDeclaration(List<Token> tokens){
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
            throw new SyntaxException(tokens.get(colonIndex + 1));
        }

        final DeclarationNode node = new DeclarationNode(tokens.get(0));
        node.setLeft(new IdentifierNode(tokens.get(colonIndex - 1)));
        node.setRight(new TypeNode(tokens.get(colonIndex + 1)));

        return node;
    }

    private AbstractNode processValue(List<Token> tokens){
        final Stack<Token> postFix = ShuntingYard.process(tokens);
        return processShuntingYard(postFix);
    }

}
