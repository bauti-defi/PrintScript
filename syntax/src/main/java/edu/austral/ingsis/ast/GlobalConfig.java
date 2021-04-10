package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.builders.DelcarationAssignationBuilder;
import edu.austral.ingsis.ast.builders.NodeBuilder;
import edu.austral.ingsis.ast.builders.ReferenceAssignationBuilder;

import java.util.Arrays;
import java.util.List;

public class GlobalConfig {

    public static List<NodeBuilder<?>> NODE_BUILDERS = Arrays.asList(
            new DelcarationAssignationBuilder(), new ReferenceAssignationBuilder()
    );
}
