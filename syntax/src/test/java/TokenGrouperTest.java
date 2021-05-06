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

    assertLinesMatch(
        groups.get(0).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList("let", "pi", ":", "number", ";"));

    assertLinesMatch(
        groups.get(1).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList("pi", "=", "3.14", ";"));

    assertLinesMatch(
        groups.get(2).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList("println", "(", "pi", "/", "2", ")", ";"));
  }

  @Test
  public void test02() {
    List<Token> tokens = readTokensFromFile("test2.txt");

    List<List<Token>> groups = TokenGrouper.group(tokens);

    assertLinesMatch(
        groups.get(0).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList("let", "numberResult", ":", "number", "=", "5", "*", "5", "-", "8", ";"));

    assertLinesMatch(
        groups.get(1).stream().map(Token::getValue).collect(Collectors.toList()),
        Arrays.asList("println", "(", "numberResult", ")", ";"));
  }
}
