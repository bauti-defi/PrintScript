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
    Lexer lexer = new Lexer("1.1");
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
  public void test000() {
    final AST ast = createAST("finaltest69.txt");

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
    assertEquals("if statement working correctly\noutside of conditional\n", baos.toString());
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

  @Test
  public void test44() {
    final AST ast = createAST("valid-const.txt");

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
    assertEquals("", baos.toString());
  }

  @Test
  public void test445() {
    final AST ast = createAST("finaltest5.txt");

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
    assertEquals("17\n", baos.toString());
  }

  @Test
  public void text05() {
    final AST ast = createAST("fullTest05.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable name is immutable.", e.getMessage());
    }
  }

  @Test
  public void text55() {
    final AST ast = createAST("finaltest3.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Unsupported binary operation: STAR_SYMBOL", e.getMessage());
    }
  }

  @Test
  public void text555() {
    final AST ast = createAST("invalid-exp-for-type.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Unsupported binary operation: STAR_SYMBOL", e.getMessage());
    }
  }

  @Test
  public void text06() {
    final AST ast = createAST("fullTest06.txt");

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
    assertEquals("no\n", baos.toString());
  }

  @Test
  public void text99() {
    final AST ast = createAST("finaltest1.txt");

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
    assertEquals("1.57\n", baos.toString());
  }

  @Test
  public void text999() {
    final AST ast = createAST("finaltest2.txt");

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
    assertEquals("5\n", baos.toString());
  }

  @Test
  public void text07() {
    final AST ast = createAST("fullTest07.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable state is of type boolean.", e.getMessage());
    }
  }

  @Test
  public void text08() {
    final AST ast = createAST("fullTest08.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable count is of type number.", e.getMessage());
    }
  }

  @Test
  public void text09() {
    final AST ast = createAST("fullTest09.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable count is of type number.", e.getMessage());
    }
  }

  @Test
  public void text10() {
    final AST ast = createAST("fullTest10.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable state is of type boolean.", e.getMessage());
    }
  }

  @Test
  public void text11() {
    final AST ast = createAST("fullTest11.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable bool is undefined.", e.getMessage());
    }
  }

  @Test
  public void text12() {
    final AST ast = createAST("fullTest12.txt");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable bool is undefined.", e.getMessage());
    }
  }

  @Test
  public void text13() {
    final AST ast = createAST("fullTest13.txt");

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
    assertEquals("no\n", baos.toString());
  }
}
