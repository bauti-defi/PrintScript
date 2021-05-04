package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.util.Keywords;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Lexer {

  @Builder.Default private final Map<String, TokenType> keywords = Keywords.getKeyword1_0();

  public List<Token> tokenize(List<String> document) {
    List<String> strings;
    List<Token> tokens = new ArrayList<>();
    for (String line : document) {
      strings = Arrays.asList(line.split(" "));
      for (String s : strings) tokens.add(getToken(s, strings.indexOf(s), document.indexOf(line)));
    }
    return tokens;
  }

  private Token getToken(String s, Integer index, Integer line) {

    if (keywords.containsKey(s))
      return Token.builder().value(s).type(keywords.get(s)).index(index).line(line).build();

    if (isString(s))
      return Token.builder()
          .value("\"" + s + "\"")
          .type(TokenType.LITERAL)
          .index(index)
          .line(line)
          .build();

    if (isNumber(s))
      return Token.builder().value(s).type(TokenType.LITERAL).index(index).line(line).build();

    return Token.builder().value(s).type(TokenType.IDENTIFIER).index(index).line(line).build();
  }

  private Boolean isNumber(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private Boolean isString(String s) {
    return s.charAt(0) == s.charAt(s.length() - 1) && (s.charAt(0) == 34 || s.charAt(0) == 39);
  }
}
