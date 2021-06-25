package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import edu.austral.ingsis.util.CompoundKeywords;
import edu.austral.ingsis.util.Keywords;
import edu.austral.ingsis.util.StateType;
import edu.austral.ingsis.util.WordsToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lexer {

  private Map<String, TokenType> keyWords = Keywords.getKeyword1_1();
  private Map<String, TokenType> wordsKeyWords = WordsToken.getWords1_1();
  private Map<String, TokenType> compoundKeywords = CompoundKeywords.getCompoundKeywords();
  private String accum = "";
  private StateType state = StateType.EMPTY;
  private List<Token> tokens = new ArrayList<>();
  private Integer index = 0;

  public Lexer(String version) {
    if (version.equals("1.0")) {
      keyWords = Keywords.getKeyword1_0();
      wordsKeyWords = WordsToken.getWords1_0();
    } else if (version.equals("1.1")) {
      keyWords = Keywords.getKeyword1_1();
      wordsKeyWords = WordsToken.getWords1_1();
    }
  }

  public List<Token> lex(List<String> text) {
    clear();
    Integer line = 0;
    for (String s : text) {
      for (Character c : s.toCharArray()) tokenize(c, line);

      this.index = 0;
      line++;
    }
    return tokens;
  }

  private void tokenize(Character c, Integer line) {

    switch (state) {
      case STRING:
        accum += c.toString();
        if (c.toString().equals("\"")) createToken(TokenType.LITERAL, line);

        break;
      case EMPTY:
        if (isCompound(c)) addAccum(StateType.COMPOUND_KEYWORD, c);
        else if (isNumber(c)) addAccum(StateType.NUMBER, c);
        else if (isLetter(c)) addAccum(StateType.IS_LETTER, c);
        else if (c.toString().equals("\"")) addAccum(StateType.STRING, c);
        else if (accumIsWordKeyword()) createToken(wordsKeyWords.get(accum), line);
        else if (isKeyword(c)) {
          accum += c.toString();
          createToken(keyWords.get(c.toString()), line);
        }

        break;
      case NUMBER:
        if (isCompound(c)) {
          createToken(TokenType.LITERAL, line);
          changeToCompoundStateAndAddToAccum(c);
        } else if (isNumber(c)) accum += c.toString();
        else if (isKeyword(c)) {
          createToken(TokenType.LITERAL, line);
          accum += c.toString();
          createToken(keyWords.get(accum), line);
        } else if (c.toString().equals(".")) accum += c.toString();

        break;
      case IS_LETTER:
        if (isCompound(c)) {
          if (accumIsWordKeyword()) createToken(wordsKeyWords.get(accum), line);
          else createToken(TokenType.IDENTIFIER, line);
          state = StateType.COMPOUND_KEYWORD;
          accum += c.toString();
        } else if (isLetter(c)) accum += c.toString();
        else if (isKeyword(c)) {
          if (accumIsWordKeyword()) {
            createToken(wordsKeyWords.get(accum), line);
          } else {
            createToken(TokenType.IDENTIFIER, line);
          }
          accum += c.toString();
          createToken(keyWords.get(c.toString()), line);

        } else if (accumIsWordKeyword()) createToken(wordsKeyWords.get(accum), line);

        break;
      case COMPOUND_KEYWORD:
        if (isCompound(c)) accum += c.toString();
        else if (isNumber(c)) {
          createToken(wordsKeyWords.get(accum), line);
          state = StateType.NUMBER;
          accum += c.toString();
        } else if (c.toString().equals("\"")) {
          createToken(wordsKeyWords.get(accum), line);
          state = StateType.STRING;
          accum += c.toString();
        } else if (c.toString().equals(" ")) createToken(wordsKeyWords.get(accum), line);
    }
  }

  private void createToken(TokenType tokenType, Integer line) {
    tokens.add(Token.builder().value(accum).type(tokenType).index(index).line(line).build());
    this.index += 1;
    this.state = StateType.EMPTY;
    accum = "";
  }

  private boolean isKeyword(Character c) {
    return keyWords.containsKey(c.toString());
  }

  private boolean isCompound(Character c) {
    //    return c.toString().equals("=") || c.toString().equals("<") || c.toString().equals(">");
    return compoundKeywords.containsKey(c.toString());
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

  private boolean accumIsWordKeyword() {
    return wordsKeyWords.containsKey(accum);
  }

  private void changeToCompoundStateAndAddToAccum(Character c) {
    state = StateType.COMPOUND_KEYWORD;
    accum += c.toString();
  }

  private void addAccum(StateType state, Character c) {
    this.state = state;
    accum += c.toString();
  }

  private void clear() {
    accum = "";
    state = StateType.EMPTY;
    tokens.clear();
    index = 0;
  }
}
