package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.util.Keywords;
import edu.austral.ingsis.util.StateType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lexer_1_1 {

  private Map<String, TokenType> keyWords = Keywords.getKeyword1_1();
  private String accum = "";
  private StateType state;
  private List<Token> tokens = new ArrayList<>();

  public List<Token> lex(List<String> text) {
    Integer index = 0;
    Integer lineNumber = 0;
    for (String s : text) {
      List<Character> line = s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
      for (Character c : line) {
        tokenize(c, index, lineNumber);
        index++;
      }
      lineNumber++;
    }
    return tokens;
  }

  private void tokenize(Character c, Integer index, Integer lineNumber) {
    if (c.toString().equals("\"")) {
      if (accum.isEmpty()) {
        state = StateType.STRING;
        accum = accum + "\"";
      } else if (state.equals(StateType.STRING)) {
        accum.concat("\"");
        createToken(TokenType.LITERAL, index, lineNumber);
      }
    } else if (state.equals(StateType.STRING)) accum.concat(c.toString());
    else if (accum.isEmpty() && isNumber(c)) {
      accum.concat(c.toString());
      state = StateType.NUMBER;
    } else if (state.equals(StateType.NUMBER) && isNumber(c)) accum.concat(c.toString());
    else if (accum.isEmpty() && isLetter(c)) {
      state = StateType.IS_LETTER;
      accum.concat(c.toString());
    } else if (isLetter(c) && state.equals(StateType.IS_LETTER)) {
      accum.concat(c.toString());
      if (accumIsKeyboard()) {
        createToken(keyWords.get(accum), index, lineNumber);
      }
    } else if (isKeyword(c) && !accum.isEmpty()) {
      if (state.equals(StateType.NUMBER) && !isNumber(c))
        createToken(TokenType.LITERAL, index, lineNumber);
      else if (state.equals(StateType.IS_LETTER))
        tokens.add(
            Token.builder()
                .value(accum)
                .type(TokenType.IDENTIFIER)
                .index(index)
                .line(lineNumber)
                .build());
      tokens.add(
          Token.builder()
              .value(c.toString())
              .type(keyWords.get(c.toString()))
              .index(index)
              .line(lineNumber)
              .build());
    }
  }

  private void createToken(TokenType tokenType, Integer index, Integer line) {
    tokens.add(Token.builder().value(accum).type(tokenType).index(index).line(line).build());
    accum = "";
  }

  private boolean isKeyword(Character c) {
    return keyWords.containsKey(c.toString());
  }

  private boolean accumIsKeyboard() {
    return keyWords.containsKey(accum);
  }

  private boolean isLetter(Character c) {
    return Character.isLetter(c);
  }

  private boolean isNumber(Character c) {
    return Character.isDigit(c);
  }
}
