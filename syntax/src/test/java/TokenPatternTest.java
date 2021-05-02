import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.ast.TokenPattern;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TokenPatternTest implements TokenHelper {

  @Test
  public void testContain() {}

  @Test
  public void testEndsWith() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    // let x:
    final TokenPattern pattern =
        TokenPattern.Builder.of(TokenType.IDENTIFIER).colon().type().build();

    assertEquals(true, pattern.endsWith(tokens));
  }

  @Test
  public void testStartsWith() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    // let x:
    final TokenPattern pattern =
        TokenPattern.Builder.of(TokenType.LET).identifier().colon().build();

    assertEquals(true, pattern.startWith(tokens));
  }

  @Test
  public void testMatches() {
    // let x:number
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE));

    final TokenPattern pattern =
        TokenPattern.Builder.of(TokenType.LET).identifier().colon().type().build();

    assertEquals(true, pattern.matches(tokens));
  }
}
