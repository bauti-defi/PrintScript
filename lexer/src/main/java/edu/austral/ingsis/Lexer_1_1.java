package edu.austral.ingsis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lexer_1_1 {

    private Map<String, TokenType> keyWords = Keywords.getKeyword();
    private List<Character> accum = new ArrayList<>();
    private List<Token> lineTokens = new ArrayList<>();

    public List<Token> lex(String text){
        List<Character> line = text
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        for (Character c: line) {
            tokenize(c);
        }
        return lineTokens;
    }

    private void tokenize(Character c){
        if (isKeyword(c)){
            lineTokens.add(Token.builder().type(keyWords.get(accum)).index(1).line(1).build());
            lineTokens.add(Token.builder().type(keyWords.get(c.toString())).index(1).line(1).build());
            accum.clear();
            return;
        }
        if (isNumber(c)){
            if (accumIsNumber()) lineTokens.add(Token.builder().type(TokenType.LITERAL).line(1).index(1).build());

        }
    }

    private boolean isKeyword(Character c){
        return keyWords.containsKey(accum) || keyWords.containsKey(c.toString());
    }
    private boolean isLetter (Character c){
        return Character.isLetter(c);
    }

    private boolean isNumber(Character c){
        return Character.isDigit(c);
    }

    private boolean accumIsNumber(){
        for (Character c: accum){
            if (Character.isLetter(c)) return false;
        }
        return true;
    }
}
