package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.nodes.*;

public interface ExpressionVisitor<T> {

  T visit(BinaryOpNode node); // string or number

  T visit(ValueLiteralNode node); // string or number, boolean

  T visit(ExpressionNode node); // string or number or boolean

  T visit(ReferenceNode node); // string, number boolean

  boolean visit(LogicalOpNode node); // boolean
}
