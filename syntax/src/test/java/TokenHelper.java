import edu.austral.ingsis.Token;
import edu.austral.ingsis.TokenType;

public interface TokenHelper {

  default Token createMockToken(String value, TokenType type) {
    return Token.builder().index(0).line(0).type(type).value(value).build();
  }
}
