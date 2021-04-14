package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.builders.DelcarationAssignationParser;
import edu.austral.ingsis.ast.builders.NodeParser;
import edu.austral.ingsis.ast.builders.ReferenceAssignationParser;

import java.util.Arrays;
import java.util.List;

public class GlobalConfig {

    public static List<NodeParser<?>> NODE_BUILDERS = Arrays.asList(
            new DelcarationAssignationParser(), new ReferenceAssignationParser()
    );
}
