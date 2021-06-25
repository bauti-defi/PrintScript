import static org.junit.jupiter.api.Assertions.*;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.interpreter.Interpreter;
import edu.austral.ingsis.tokens.Token;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FullTest {

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
  public void test01() {
    final AST ast = createAST("fullTest01.txt", "1.1");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected =
        Arrays.asList("else statement working correctly", "outside of conditional");
    assertList(expected, out.out);
  }

  public void assertList(List<String> expected, List<String> actual) {
    assertTrue(expected.size() == actual.size());
    for (int i = 0; i < actual.size(); i++) {
      assertEquals(expected.get(i), actual.get(i));
    }
  }

  @Test
  public void test02() {
    final AST ast = createAST("fullTest02.txt", "1.0"); // error

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("hello world 1");
    assertList(expected, out.out);
  }

  @Test
  public void test03() {
    final AST ast = createAST("fullTest03.txt", "1.1");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected =
        Arrays.asList("if statement working correctly", "outside of conditional");
    assertList(expected, out.out);
  }

  @Test
  public void test04() {
    final AST ast = createAST("fullTest04.txt", "1.1");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("Worked!");
    assertList(expected, out.out);
  }

  @Test
  public void test44() {
    final AST ast = createAST("valid-const.txt", "1.1");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    assertTrue(out.out.size() == 0);
  }

  @Test
  public void testFinal5() {
    final AST ast = createAST("finaltest5.txt", "1.0");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("17");
    assertList(expected, out.out);
  }

  @Test
  public void test023() {
    final AST ast = createAST("finaltest23.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Unknown Syntax error only booleans allowed in if statements", e.getMessage());
    }
  }

  @Test
  public void test05() {
    final AST ast = createAST("fullTest05.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable name is immutable.", e.getMessage());
    }
  }

  @Test
  public void test099() {
    final AST ast = createAST("finaltest99.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable b is immutable.", e.getMessage());
    }
  }

  @Test
  public void testFinal3() {
    final AST ast = createAST("finaltest3.txt", "1.0");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals(
          "Unknown Syntax error Unsupported binary operation: STAR_SYMBOL", e.getMessage());
    }
  }

  @Test
  public void testInvalidExp() {
    final AST ast = createAST("invalid-exp-for-type.txt", "1.0");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable pi is of type number.", e.getMessage());
    }
  }

  @Test
  public void test06() {
    final AST ast = createAST("fullTest06.txt", "1.0");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("no");
    assertList(expected, out.out);
  }

  @Test
  public void testFinal1() {
    final AST ast = createAST("finaltest1.txt", "1.0");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("1.57");
    assertList(expected, out.out);
  }

  @Test
  public void testFinal2() {
    final AST ast = createAST("finaltest2.txt", "1.0");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("5");
    assertList(expected, out.out);
  }

  @Test
  public void test07() {
    final AST ast = createAST("fullTest07.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable state is of type boolean.", e.getMessage());
    }
  }

  @Test
  public void test08() {
    final AST ast = createAST("fullTest08.txt", "1.0");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable count is of type number.", e.getMessage());
    }
  }

  @Test
  public void test09() {
    final AST ast = createAST("fullTest09.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable count is of type number.", e.getMessage());
    }
  }

  @Test
  public void test10() {
    final AST ast = createAST("fullTest10.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable state is of type boolean.", e.getMessage());
    }
  }

  @Test
  public void test11() {
    final AST ast = createAST("fullTest11.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable bool is undefined.", e.getMessage());
    }
  }

  @Test
  public void test12() {
    final AST ast = createAST("fullTest12.txt", "1.1");

    try {
      Interpreter.interpret(ast);
    } catch (Exception e) {
      assertEquals("Variable bool is undefined.", e.getMessage());
    }
  }

  @Test
  public void test13() {
    final AST ast = createAST("fullTest13.txt", "1.1");

    final StdOutTester out = new StdOutTester();

    Interpreter.interpret(ast, out);

    List<String> expected = Arrays.asList("no");
    assertList(expected, out.out);
  }
}
