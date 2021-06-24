import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.ast.TokenPattern;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TokenPatternTest implements TokenHelper {

  @Test
  public void testNormal() {
    // 5+5-5*5
    List<Token> tokens =
            Arrays.asList(
                    createMockToken("5", TokenType.LITERAL),
                    createMockToken("+", TokenType.PLUS_SYMBOL),
                    createMockToken("5", TokenType.LITERAL),
                    createMockToken("-", TokenType.MINUS_SYMBOL),
                    createMockToken("5", TokenType.LITERAL),
                    createMockToken("*", TokenType.STAR_SYMBOL),
                    createMockToken("5", TokenType.LITERAL));

    final TokenPattern pattern = TokenPattern.Builder.of(TokenType.LITERAL).plus().literal().minus().literal().star().literal().build();

    assertEquals(true, pattern.endsWith(tokens));

  }

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
