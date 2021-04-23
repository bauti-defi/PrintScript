package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.TokenType;
import edu.austral.ingsis.ast.nodes.*;
import lombok.SneakyThrows;

public class StringExpressionVisitor implements ExpressionVisitor<String> {

    private final Context context;

    private StringExpressionVisitor(Context context) {
        this.context = context;
    }

    public static String process(ExpressionNode node, Context context) {
        final StringExpressionVisitor visitor = new StringExpressionVisitor(context);
        return visitor.visit(node);
    }

    @SneakyThrows
    @Override
    public String visit(BinaryOpNode node) {
        if(node.getToken().getType() == TokenType.PLUS_SYMBOL){
            return this.visit(node.getLeft()) + this.visit(node.getRight());
        }
        throw new Exception("Unsupported binary operation: " + node.getToken().getType());
    }

    @Override
    public String visit(ValueLiteralNode node) {
        return node.getToken().getValue().replaceAll("\"", "");
    }

    @SneakyThrows
    @Override
    public String visit(ExpressionNode node) {
        switch (node.getNodeType()) {
            case "BINARY_EXPRESSION":
                return this.visit((BinaryOpNode) node);
            case "VALUE_LITERAL":
                return this.visit((ValueLiteralNode) node);
            case "REFERENCE":
                return this.visit((ReferenceNode) node);
        }
        throw new Exception("Unknown symbol: " + node.getToken().getValue());
    }

    @SneakyThrows
    @Override
    public String visit(ReferenceNode node) {
        return this.context.getVariables().getValue(node.getIdentifier());
    }

    @Override
    public boolean visit(LogicalOpNode node) {
        return false;
    }
}
