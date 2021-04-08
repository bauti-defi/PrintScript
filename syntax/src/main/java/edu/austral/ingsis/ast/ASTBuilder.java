package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.builders.NodeBuilder;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.exceptions.SemicolonAbsentException;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.exceptions.SyntaxTokenExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class ASTBuilder implements TokenHelper{

    private final List<NodeBuilder<?>> builders;
    final DeclarationTable declarations = new DeclarationTable();
    final List<AbstractNode> nodes = new ArrayList<>();

    public ASTBuilder(List<NodeBuilder<?>> builders) {
        this.builders = builders;
    }

    public void process(List<Token> tokens){
        int lineCount = tokens.get(tokens.size() - 1).getLine();
        final Map<Integer, List<Token>> lines = tokens.stream().collect(Collectors.groupingBy(Token::getLine));

        for(int i = 0; i < lineCount; i++){
            final List<Token> line = lines.get(i);
            if(!endsWithSemicolon(line)){
                throw new SemicolonAbsentException(line.get(line.size() - 1));
            }

            final AbstractNode node = builders.stream().filter(builder -> builder.predicate(line, declarations))
                    .findFirst().map(builder -> builder.build(trimSemicolon(line), declarations))
                    .orElseThrow(() -> new SyntaxException());

            //process without semicolon
            nodes.add(node);
        }
    }

    private List<Token> trimSemicolon(List<Token> line){
        return line.subList(0, line.size() - 1);
    }

   private boolean endsWithSemicolon(List<Token> tokens){
        return endsWith(tokens, TokenType.SEMICOLON);
    }


}
