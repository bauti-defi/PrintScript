import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.List;

public interface TokenHelper {

  default Token createMockToken(String value, TokenType type) {
    return Token.builder().index(0).line(0).type(type).value(value).build();
  }

  default List<Token> readTokensFromFile(String filename) {
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.tokenize(FileReaderPS.read("src/test/java/resources/" + filename));
    tokens.forEach(System.out::println);
    return tokens;
  }
}
