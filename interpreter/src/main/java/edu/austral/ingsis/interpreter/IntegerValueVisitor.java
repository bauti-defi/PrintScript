package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.ReferenceNode;
import edu.austral.ingsis.ast.nodes.ValueLiteralNode;
import lombok.SneakyThrows;

public class IntegerValueVisitor implements ValueVisitor<Integer> {

  private final Context context;

  public IntegerValueVisitor(Context context) {
    this.context = context;
  }

  @SneakyThrows
  @Override
  public Integer visit(ReferenceNode node) {
    return Integer.valueOf(context.getVariables().getValue(node.getIdentifier()));
  }

  @Override
  public Integer visit(ValueLiteralNode node) {
    return Integer.valueOf(node.getToken().getValue());
  }
}
