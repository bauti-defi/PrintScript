import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import edu.austral.ingsis.ast.TokenGrouper;
import edu.austral.ingsis.tokens.Token;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TokenGrouperTest implements TokenHelper {

  @Test
  public void test01() {
    List<Token> tokens = readTokensFromFile("test01-grouper.txt");

    List<List<Token>> groups = TokenGrouper.group(tokens);

    testLine(groups, 0, "let", "pi", ":", "number", ";");

    testLine(groups, 1, "pi", "=", "3.14", ";");

    testLine(groups, 2, "println", "(", "pi", "/", "2", ")", ";");

    assertEquals(3, groups.size());
  }

  @Test
  public void test02() {
    List<Token> tokens = readTokensFromFile("test2.txt");

    List<List<Token>> groups = TokenGrouper.group(tokens);

    testLine(groups, 0, "let", "numberResult", ":", "number", "=", "5", "*", "5", "-", "8", ";");

    testLine(groups, 1, "println", "(", "numberResult", ")", ";");
  }

  @Test
  public void test03() {
    List<Token> tokens = readTokensFromFile("test3.txt");

    List<List<Token>> groups = TokenGrouper.group(tokens);

    testLine(
        groups, 0, "let", "cuenta", ":", "number", "=", "5", "*", "5", "-", "8", "/", "4", "+", "2",
        ";");

    assertEquals(1, groups.size());
  }

  @Test
  public void test04() {
    List<Token> tokens = readTokensFromFile("test4.txt");

    List<List<Token>> groups = TokenGrouper.group(tokens);

    testLine(groups, 0, "const", "booleanResult", ":", "boolean", "=", "5", "<=", "3", ";");

    testLine(
        groups,
        1,
        "if",
        "(",
        "booleanResult",
        ")",
        "{",
        "}",
        "else",
        "{",
        "println",
        "(",
        "\"else statement working correctly\"",
        ")",
        ";",
        "}");

    testLine(groups, 2, "println", "(", "\"outside of conditional\"", ")", ";");

    assertEquals(3, groups.size());
  }

  private void testLine(List<List<Token>> groups, int group, String... pattern) {
    assertLinesMatch(
        groups.get(group).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList(pattern));
  }
}
