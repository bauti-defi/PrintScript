package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.nodes.AbstractNode;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.ast.nodes.ReferenceAssignationNode;

public class Interpreter {

  public void execute(AST ast) {
    final Context context = new Context();
    ast.getNodes().forEach(node -> execute(node, context));
  }

  private void execute(AbstractNode node, Context context) {
    switch (node.getNodeType()) {
      case "DECLARATION_ASSIGNATION":
        ASTVisitor.create(context).visit((DeclarationAssignationNode) node);
        break;
      case "REFERENCE_ASSIGNATION":
        ASTVisitor.create(context).visit((ReferenceAssignationNode) node);
        break;
    }
  }
}
