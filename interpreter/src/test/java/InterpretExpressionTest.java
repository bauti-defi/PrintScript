import static org.junit.jupiter.api.Assertions.*;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import edu.austral.ingsis.interpreter.Interpreter;
import edu.austral.ingsis.tokens.Token;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InterpretExpressionTest {

  private AST createAST(String filename, String version) {
    Lexer lexer = new Lexer(version);
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/scripts/" + filename));
    return AST.create(
        tokens,
        version.equals("1.1")
            ? GlobalASTConfig.NODE_PARSERS_V_1_1
            : GlobalASTConfig.NODE_PARSERS_V_1_0);
  }

  @Test
  public void testStringDeclaration() {
    final AST ast = createAST("testStringDeclaration.txt", "1.0");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertTrue(!context.isUndefined("message"));
    assertFalse(context.getDeclaration("message").isImmutable());
    assertEquals("string", context.getDeclaration("message").getType());

    try {
      assertEquals("hola", context.getValue("message"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testStringConcat() {
    final AST ast = createAST("testStringConcat.txt", "1.0");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertTrue(!context.isUndefined("name"));
    assertFalse(context.getDeclaration("name").isImmutable());
    assertEquals("string", context.getDeclaration("name").getType());

    try {
      assertEquals("BautistaBaiocchi", context.getValue("name"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testStringNumberConcat() {
    final AST ast = createAST("testStringNumberConcat.txt", "1.0");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertTrue(!context.isUndefined("name"));
    assertFalse(context.getDeclaration("name").isImmutable());
    assertEquals("string", context.getDeclaration("name").getType());

    try {
      assertEquals("Bautista12345", context.getValue("name"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testNumberDeclaration() {
    final AST ast = createAST("testNumberDeclaration.txt", "1.0");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertFalse(context.isUndefined("x"));
    assertFalse(context.getDeclaration("x").isImmutable());
    assertEquals("number", context.getDeclaration("x").getType());

    try {
      assertEquals("34", context.getValue("x"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //  @Test
  public void testConstDeclaration() {
    final AST ast = createAST("testConstDeclaration.txt", "1.1");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertTrue(!context.isUndefined("x"));
    assertTrue(context.getDeclaration("x").isImmutable());
    assertEquals("number", context.getDeclaration("x").getType());
  }

  @Test
  public void testBooleanDeclaration() {
    final AST ast = createAST("testComplexBooleanDeclaration.txt", "1.1");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((DeclarationAssignationNode) ast.getBlock().getNodes().get(0));

    assertFalse(context.isUndefined("x"));
    assertFalse(context.getDeclaration("x").isImmutable());
    assertEquals("boolean", context.getDeclaration("x").getType());

    try {
      assertEquals("false", context.getValue("x"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
