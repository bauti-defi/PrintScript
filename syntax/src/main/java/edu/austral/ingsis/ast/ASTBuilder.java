package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.tokens.Token;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder {

  private final List<NodeParser<?>> builders;
  final List<AbstractNode> nodes = new ArrayList<>();

  public ASTBuilder(List<NodeParser<?>> builders) {
    this.builders = builders;
  }

  public void process(List<Token> tokens) {
    List<List<Token>> tokenGroups = TokenGrouper.group(tokens);

    outter:
    for (int i = 0; i < tokenGroups.size(); i++) {
      for (NodeParser parser : builders) {
        if (parser.predicate(tokenGroups.get(i))) {
          nodes.add(parser.parse(tokenGroups.get(i)));
          continue outter;
        }
      }
      if (i < tokenGroups.size()) {
        throw new SyntaxException("AST builder");
      }
    }
  }
}
