package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.builders.AssignationBuilder;
import edu.austral.ingsis.ast.builders.NodeBuilder;

import java.util.Arrays;
import java.util.List;

public class GlobalConfig {

    public static List<NodeBuilder<?>> NODE_BUILDERS = Arrays.asList(
            new AssignationBuilder()
    );
}
