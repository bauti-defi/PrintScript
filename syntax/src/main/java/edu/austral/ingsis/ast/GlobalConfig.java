package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.parsers.DelcarationAssignationParser;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.ast.parsers.PrintlnParser;
import edu.austral.ingsis.ast.parsers.ReferenceAssignationParser;

import java.util.Arrays;
import java.util.List;

public class GlobalConfig {

    public static List<NodeParser<?>> NODE_BUILDERS = Arrays.asList(
            new DelcarationAssignationParser(), new ReferenceAssignationParser(),
            new PrintlnParser()
    );
}
