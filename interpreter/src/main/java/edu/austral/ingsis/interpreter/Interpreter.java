package edu.austral.ingsis.interpreter;

import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.nodes.*;

import java.util.function.Consumer;

public class Interpreter {

  public static void interpret(AST ast) {
    final Context context = new Context();
    ASTVisitor.create(context).visit(ast.getBlock());
  }

  public static void interpret(AST ast, Context parentContext) {
    final Context context = new Context(parentContext);
    ASTVisitor.create(context).visit(ast.getBlock());
  }


  public static void interpret(AST ast, Consumer<String> stdOut) {
    final Context context = new Context();
    ASTVisitor.create(context).visit(ast.getBlock());
  }

  public static void interpret(AST ast, Context parentContext, Consumer<String> stdOut) {
    final Context context = new Context(parentContext);
    ASTVisitor.create(context).visit(ast.getBlock());
  }
}
