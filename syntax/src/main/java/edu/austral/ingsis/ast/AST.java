package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.AbstractNode;
import java.util.List;

public class AST {

  private final List<AbstractNode> nodes;

  private AST(ASTBuilder builder) {
    this.nodes = builder.nodes;
  }

  public List<AbstractNode> getNodes() {
    return nodes;
  }

  public static AST create(List<Token> tokens) {
    final ASTBuilder builder = new ASTBuilder(GlobalConfig.NODE_BUILDERS);
    builder.process(tokens);
    return new AST(builder);
  }
}
