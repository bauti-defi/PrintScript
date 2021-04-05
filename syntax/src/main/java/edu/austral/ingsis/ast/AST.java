package edu.austral.ingsis.ast;

import java.util.List;

public class AST {

    private final NodeBlock block;

    public AST(NodeBlock block) {
        this.block = block;
    }

    public List<Node> getNodes() {
        return this.block.getNodes();
    }
}
