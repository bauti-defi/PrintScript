package edu.austral.ingsis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lexer {

    Map<String, TokenType> keyWords;
    List<String> numbers;

    public List<Token> tokenize(String line){
        return null;
    }

    public List<List<Token>> tokenize(String[] document){
        return null;
    }

    private Token getToken(String s, Integer index, Integer line){

        if (keyWords.containsKey(s)) return Token.builder().value(s).tokenType(keyWords.get(s)).index(1).line(1).build();

        if (s.charAt(0) == s.charAt(s.length() - 1) && (s.charAt(0) == 34 || s.charAt(0) == 39) ) return Token.builder().value(s).tokenType(TokenType.STRING).index(1).line(1).build();

        if (numbers.contains(s)) return Token.builder().value(s).tokenType(TokenType.NUMBER).index(1).line(1).build();

        return Token.builder().value(s).tokenType(TokenType.ID).index(1).line(1).build();
    }

}
