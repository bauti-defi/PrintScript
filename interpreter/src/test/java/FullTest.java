import static org.junit.jupiter.api.Assertions.*;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.interpreter.Interpreter;
import edu.austral.ingsis.tokens.Token;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FullTest {

  private AST createAST(String filename) {
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/scripts/" + filename));
    return AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
  }

  @Test
  public void test01() {
    final AST ast = createAST("fullTest01.txt");

    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);

    Interpreter.interpret(ast);

    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    System.out.println(baos.toString());
    assertEquals(
        "else statement working correctly\n" + "outside of conditional\n", baos.toString());
  }

  @Test
  public void test02() {
    final AST ast = createAST("fullTest02.txt");

    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);

    Interpreter.interpret(ast);

    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    System.out.println(baos.toString());
    assertEquals("hello world 1\n", baos.toString());
  }

  @Test
  public void test03() {
    final AST ast = createAST("fullTest03.txt");

    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);

    Interpreter.interpret(ast);

    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    System.out.println(baos.toString());
    assertEquals("if statement working correctly\n" + "outside of conditional\n", baos.toString());
  }

  @Test
  public void test04() {
    final AST ast = createAST("fullTest04.txt");

    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);

    Interpreter.interpret(ast);

    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    System.out.println(baos.toString());
    assertEquals("Worked!\n", baos.toString());
  }
}
