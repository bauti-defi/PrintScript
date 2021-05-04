package edu.austral.ingsis.ast.nodes;

import java.util.List;

public class CodeBlock {

  private final List<AbstractNode> nodes;

  public CodeBlock(List<AbstractNode> nodes) {
    this.nodes = nodes;
  }

  public List<AbstractNode> getNodes() {
    return nodes;
  }
}
