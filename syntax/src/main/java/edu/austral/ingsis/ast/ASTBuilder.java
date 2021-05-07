package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.exceptions.SemicolonAbsentException;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public class ASTBuilder {

  private final List<NodeParser<?>> builders;
  final List<AbstractNode> nodes = new ArrayList<>();

  public ASTBuilder(List<NodeParser<?>> builders) {
    this.builders = builders;
  }

  public void process(List<Token> tokens) {
    List<List<Token>> tokenGroups = TokenGrouper.group(tokens);

    for(List<Token> group: tokenGroups){
      // process without semicolon
      final AbstractNode node =
              builders.stream()
                      .filter(builder -> builder.predicate(group))
                      .findFirst()
                      .map(builder -> builder.parse(group))
                      .orElseThrow(() -> new SyntaxException());

      nodes.add(node);
    }


  }

}
