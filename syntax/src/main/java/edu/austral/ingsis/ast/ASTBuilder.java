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

  public void process01(List<Token> tokens) {
    int ifStartFlag = -1;
    int ifEndFlag = -1;
    int elseFlag = -1;
    for (int i = 0; i < tokens.size(); i++) {
      if (tokens.get(i).getType() == TokenType.IF) {
        ifStartFlag = i;
        // try parse if
      } else if (tokens.get(i).getType() == TokenType.SEMICOLON && ifStartFlag == -1) {
        // parse normal line
      } else if (tokens.get(i).getType() == TokenType.R_CURLY_BRACE) {
        ifEndFlag = i;
        if (tokens.size() > i && tokens.get(i + 1).getType() == TokenType.ELSE) {
          continue; // ignore because there is an else statement
        }

      } else if (tokens.get(i).getType() == TokenType.ELSE) {
        elseFlag = i;
      }
    }
  }

  private List<Token> trimSemicolon(List<Token> line) {
    return line.subList(0, line.size() - 1);
  }

  private boolean endsWithSemicolon(List<Token> tokens) {
    return TokenPattern.Builder.of(TokenType.SEMICOLON).build().endsWith(tokens);
  }
}
