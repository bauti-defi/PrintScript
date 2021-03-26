package edu.austral.ingsis.ast;

import edu.austral.ingsis.SyntaxTable;
import edu.austral.ingsis.ast.nodes.AssignationNode;
import edu.austral.ingsis.tokens.SyntaxToken;
import edu.austral.ingsis.tokens.TokenType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ASTFactory {


    public void create(List<SyntaxToken> tokens, SyntaxTable table){
        final NodeBlock block =  new NodeBlock();

        int lineCount = tokens.get(tokens.size() - 1).getLineNumber();
        final Map<Integer, List<SyntaxToken>> lines = tokens.stream().collect(Collectors.groupingBy(SyntaxToken::getLineNumber));

        for(int line = 0;line < lineCount;line++){
            final Node node = processLine(lines.get(line));
            block.addNode(node);
        }
    }

    public Node processLine(List<SyntaxToken> line){

        return null;
    }

    public AssignationNode buildAssignationNode(List<SyntaxToken> line){

        return null;
    }



}
