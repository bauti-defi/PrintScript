import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

public interface TokenHelper {

  default Token createMockToken(String value, TokenType type) {
    return Token.builder().index(0).line(0).type(type).value(value).build();
  }
}
