package edu.austral.ingsis.ast;

import edu.austral.ingsis.SyntaxTable;
import edu.austral.ingsis.ast.nodes.AssignationNode;
import edu.austral.ingsis.ast.nodes.DeclarationNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;
import edu.austral.ingsis.exceptions.SemicolonAbscentException;
import edu.austral.ingsis.exceptions.SyntaxException;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ASTFactory {


    public void create(List<Token> tokens, SyntaxTable table){
        final NodeBlock block =  new NodeBlock();

        int lineCount = tokens.get(tokens.size() - 1).getLineNumber();
        final Map<Integer, List<Token>> lines = tokens.stream().collect(Collectors.groupingBy(Token::getLineNumber));

        for(int i = 0;i < lineCount;i++){
            final List<Token> line = lines.get(i);
            if(!endsWithSemicolon(line)){
                throw new SemicolonAbscentException(line.get(line.size() - 1));
            }

            processLine(line);
        }
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

    public Node processLine(List<Token> line){
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

        final AssignationNode node = new AssignationNode(line.get(index));
        node.setLeft(processDeclaration(declaration));
        node.setRight(processValue(value));

        return node;
    }

    private DeclarationNode processDeclaration(List<Token> tokens){
       return  null;
    }

    private ValueLiteralNode processValue(List<Token> tokens){

        return null;
    }



}
