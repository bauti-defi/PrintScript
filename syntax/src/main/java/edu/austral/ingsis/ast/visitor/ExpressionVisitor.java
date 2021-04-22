package edu.austral.ingsis.ast.visitor;

import edu.austral.ingsis.ast.nodes.*;

public interface ExpressionVisitor<T> {

    Integer visit(BinaryOpNode node);//string or number

    String visit(ValueLiteralNode node);//string or number, boolean

    T visit(ExpressionNode node);//string or number or boolean

    String visit(ReferenceNode node); // string, number boolean

    boolean visit(LogicalOpNode node); //boolean

}
