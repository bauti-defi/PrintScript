package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.ReferenceNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;
import lombok.SneakyThrows;

public class ValueVisitorImpl implements ValueVisitor<String> {

  private final Context context;

  public ValueVisitorImpl(Context context) {
    this.context = context;
  }

  @SneakyThrows
  @Override
  public String visit(ReferenceNode node) {
    return context.getVariables().getValue(node.getIdentifier());
  }

  @Override
  public String visit(ValueLiteralNode node) {
    return node.getToken().getValue();
  }
}
