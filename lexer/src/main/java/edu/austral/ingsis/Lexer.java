package edu.austral.ingsis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Lexer {

    Map<String, TokenType> keyWords;

    public List<Token> manager(String filename){

        List<String> document = readFile(filename);
        return tokenize(document);

    }

    private List<String> readFile(String filename){
        List<String> document = new ArrayList<>();
        try {
            File statements = new File(filename);
            Scanner reader = new Scanner(statements);
            while (reader.hasNextLine()) {
                document.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return document;
    }

    public List<Token> tokenize(List<String> document){
        List<String> strings;
        List<Token> tokens = new ArrayList<>();
        for (String line: document) {
            strings = Arrays.asList(line.split(" "));
            for (String s: strings) tokens.add(getToken(s, strings.indexOf(s), document.indexOf(line)));
        }
        return tokens;
    }

    private Token getToken(String s, Integer index, Integer line){

        if (keyWords.containsKey(s)) return Token.builder().value(s).tokenType(keyWords.get(s)).index(index).line(line).build();

        if (s.charAt(0) == s.charAt(s.length() - 1) && (s.charAt(0) == 34 || s.charAt(0) == 39) ) return Token.builder().value(s).tokenType(TokenType.STRING_VALUE).index(index).line(line).build();

        if (isNumber(s)) return Token.builder().value(s).tokenType(TokenType.NUMBER_VALUE).index(index).line(line).build();

        return Token.builder().value(s).tokenType(TokenType.ID).index(index).line(line).build();
    }

    private Boolean isNumber(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
