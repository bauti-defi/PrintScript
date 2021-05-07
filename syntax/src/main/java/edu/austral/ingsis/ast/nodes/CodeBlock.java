package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;
import java.util.List;

public class CodeBlock implements Visitable {

  private final List<AbstractNode> nodes;

  public CodeBlock(List<AbstractNode> nodes) {
    this.nodes = nodes;
  }

  public List<AbstractNode> getNodes() {
    return nodes;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
