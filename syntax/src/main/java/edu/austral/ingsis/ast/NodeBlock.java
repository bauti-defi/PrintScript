package edu.austral.ingsis.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeBlock {

    private final List<Node> nodes = new ArrayList<>();

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node...node){
        this.nodes.addAll(Arrays.asList(node));
    }

}
