package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.tokens.Token;
import java.util.List;

public class AST {

  private final List<AbstractNode> nodes;

  private AST(ASTBuilder builder) {
    this.nodes = builder.nodes;
  }

  public List<AbstractNode> getNodes() {
    return nodes;
  }

  public static AST create(List<Token> tokens, List<NodeParser<?>> parsers) {
    final ASTBuilder builder = new ASTBuilder(parsers);
    builder.process(tokens);
    return new AST(builder);
  }
}
