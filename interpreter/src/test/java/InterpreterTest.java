import static org.junit.jupiter.api.Assertions.assertEquals;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import java.util.List;

import edu.austral.ingsis.interpreter.Interpreter;
import org.junit.jupiter.api.Test;

public class InterpreterTest implements TokenHelper {

  private final AST createAST(String filename) {
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.tokenize(FileReaderPS.read("src/test/java/scripts/" + filename));
    tokens.forEach(System.out::println);
    return AST.create(tokens);
  }

  @Test
  public void testStringDeclaration() {
    final AST ast = createAST("testStringDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertEquals(true, !context.getVariables().isUndefined("message"));
    assertEquals(false, context.getVariables().getDeclaration("message").isImmutable());
    assertEquals("string", context.getVariables().getDeclaration("message").getType());

    try {
      assertEquals("hola", context.getVariables().getValue("message"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testNumberDeclaration() {
    final AST ast = createAST("testNumberDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertEquals(false, context.getVariables().isUndefined("x"));
    assertEquals(false, context.getVariables().getDeclaration("x").isImmutable());
    assertEquals("number", context.getVariables().getDeclaration("x").getType());

    try {
      assertEquals("34", context.getVariables().getValue("x"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testConstDeclaration() {
    final AST ast = createAST("testConstDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertEquals(true, !context.getVariables().isUndefined("x"));
    assertEquals(true, context.getVariables().getDeclaration("x").isImmutable());
    assertEquals("number", context.getVariables().getDeclaration("x").getType());
  }
}
