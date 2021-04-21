import static org.junit.jupiter.api.Assertions.assertEquals;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import java.util.List;
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

    ASTVisitor.process(ast, context);

    assertEquals(true, context.getDeclarations().contains("message"));
    assertEquals(false, context.getDeclarations().get("message").isImmutable());
    assertEquals("string", context.getDeclarations().get("message").getType());
  }

  @Test
  public void testNumberDeclaration() {
    final AST ast = createAST("testNumberDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.process(ast, context);

    assertEquals(true, context.getDeclarations().contains("x"));
    assertEquals(false, context.getDeclarations().get("x").isImmutable());
    assertEquals("number", context.getDeclarations().get("x").getType());
  }

  @Test
  public void testConstDeclaration() {
    final AST ast = createAST("testConstDeclaration.txt");
    final Context context = new Context();

    ASTVisitor.process(ast, context);

    assertEquals(true, context.getDeclarations().contains("x"));
    assertEquals(true, context.getDeclarations().get("x").isImmutable());
    assertEquals("number", context.getDeclarations().get("x").getType());
  }
}
