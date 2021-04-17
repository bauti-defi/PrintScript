package edu.austral.ingsis.ast;

import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.builders.NodeParser;
import edu.austral.ingsis.ast.exceptions.SemicolonAbsentException;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ASTBuilder {

  private final List<NodeParser<?>> builders;
  final List<AbstractNode> nodes = new ArrayList<>();

  public ASTBuilder(List<NodeParser<?>> builders) {
    this.builders = builders;
  }

  public void process(List<Token> tokens) {
    final Map<Integer, List<Token>> lines =
        tokens.stream().collect(Collectors.groupingBy(Token::getLine));
    int lineCount = lines.size();

    for (int i = 0; i < lineCount; i++) {
      final List<Token> line = lines.get(i);
      if (!endsWithSemicolon(line)) {
        throw new SemicolonAbsentException(line.get(line.size() - 1));
      }

      // process without semicolon
      final AbstractNode node =
          builders.stream()
              .filter(builder -> builder.predicate(line))
              .findFirst()
              .map(builder -> builder.parse(trimSemicolon(line)))
              .orElseThrow(() -> new SyntaxException());

      nodes.add(node);
    }
  }

  private List<Token> trimSemicolon(List<Token> line) {
    return line.subList(0, line.size() - 1);
  }

  private boolean endsWithSemicolon(List<Token> tokens) {
    return TokenPattern.Builder.of(TokenType.SEMICOLON).build().endsWith(tokens);
  }
}
