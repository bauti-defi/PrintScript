package edu.austral.ingsis.ast.nodes;

import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.visitor.Visitable;
import edu.austral.ingsis.ast.visitor.Visitor;

public class PrintNode extends AbstractNode implements Visitable {

    private CompoundExpressionNode args;

    public PrintNode(Token token) {
        super(token);
    }

    public void setArgs(CompoundExpressionNode args) {
        this.args = args;
    }

    public CompoundExpressionNode getArgs() {
        return args;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
