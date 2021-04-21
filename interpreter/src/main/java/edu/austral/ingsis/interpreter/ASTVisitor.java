package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.nodes.*;
import edu.austral.ingsis.ast.visitor.Visitor;
import lombok.SneakyThrows;

public class ASTVisitor implements Visitor {

  private final Context context;

  private ASTVisitor(Context context) {
    this.context = context;
  }

  @Override
  public void visit(ReferenceAssignationNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
  }

  @Override
  public void visit(DeclarationAssignationNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
  }

  @Override
  public void visit(BinaryOpNode node) {}

  @Override
  public void visit(DeclarationNode node) {
    DeclarationVisitor.process(node, context);
  }

  @Override
  public void visit(IdentifierNode node) {}

  @Override
  public void visit(TypeNode node) {}

  @Override
  public void visit(ValueLiteralNode node) {}

  @Override
  public void visit(ExpressionNode node) {}

  @Override
  public void visit(CompoundExpressionNode node) {}

  @Override
  public void visit(PrintNode node) {}

  @Override
  public void visit(IfStatementNode node) {}

  @SneakyThrows
  @Override
  public void visit(ReferenceNode node) {
    if (!context.getDeclarations().contains(node.getIdentifier())) {
      throw new Exception("Identifier " + node.getIdentifier() + " is undefined.");
    }
  }

  public static void process(AST ast, Context context) {
    ASTVisitor visitor = new ASTVisitor(context);
    ast.getNodes()
        .forEach(
            node -> {
              switch (node.getNodeType()) {
                case "DECLARATION_ASSIGNATION":
                  ((DeclarationAssignationNode) node).accept(visitor);
                  break;
                case "REFERENCE_ASSIGNATION":
                  ((ReferenceAssignationNode) node).accept(visitor);
                  break;
              }
            });
  }
}
