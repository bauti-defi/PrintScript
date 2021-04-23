import static org.junit.jupiter.api.Assertions.*;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InterpretExpressionTest {

  private AST createAST(String filename) {
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.tokenize(FileReaderPS.read("src/test/java/scripts/" + filename));
    tokens.forEach(System.out::println);
    return AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
  }

  @Test
  public void testStringDeclaration() {
    final AST ast = createAST("testStringDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertTrue(!context.getVariables().isUndefined("message"));
    assertFalse(context.getVariables().getDeclaration("message").isImmutable());
    assertEquals("string", context.getVariables().getDeclaration("message").getType());

    try {
      assertEquals("hola", context.getVariables().getValue("message"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testStringConcat() {
    final AST ast = createAST("testStringConcat.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertTrue(!context.getVariables().isUndefined("name"));
    assertFalse(context.getVariables().getDeclaration("name").isImmutable());
    assertEquals("string", context.getVariables().getDeclaration("name").getType());

    try {
      assertEquals("BautistaBaiocchi", context.getVariables().getValue("name"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testStringNumberConcat() {
    final AST ast = createAST("testStringNumberConcat.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertTrue(!context.getVariables().isUndefined("name"));
    assertFalse(context.getVariables().getDeclaration("name").isImmutable());
    assertEquals("string", context.getVariables().getDeclaration("name").getType());

    try {
      assertEquals("Bautista12345", context.getVariables().getValue("name"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testNumberDeclaration() {
    final AST ast = createAST("testNumberDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertFalse(context.getVariables().isUndefined("x"));
    assertFalse(context.getVariables().getDeclaration("x").isImmutable());
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

    assertTrue(!context.getVariables().isUndefined("x"));
    assertTrue(context.getVariables().getDeclaration("x").isImmutable());
    assertEquals("number", context.getVariables().getDeclaration("x").getType());
  }

  // @Test
  public void testBooleanDeclaration() {
    final AST ast = createAST("testBooleanDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((DeclarationAssignationNode) ast.getNodes().get(0));

    assertFalse(context.getVariables().isUndefined("x"));
    assertFalse(context.getVariables().getDeclaration("x").isImmutable());
    assertEquals("boolean", context.getVariables().getDeclaration("x").getType());

    try {
      assertEquals("true", context.getVariables().getValue("x"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
