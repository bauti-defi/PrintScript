package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.HashMap;
import java.util.List;

public class SyntaxTable {

    private final HashMap<String, TokenType> table = new HashMap<>();

    public SyntaxTable(List<Token> tokens){
        table.put("let", TokenType.LET);
        table.put("number", TokenType.NUMBER_TYPE);
        table.put(";", TokenType.SEMICOLON);
        table.put(":", TokenType.COLON);
        table.put("=", TokenType.EQUALS);
        table.put("string", TokenType.STRING_TYPE);
        table.put("(", TokenType.L_PARENTHESES);
        table.put(")", TokenType.R_PARANTHESES);
        table.put("+", TokenType.PLUS_SYMBOL);
        table.put("-", TokenType.MINUS_SYMBOL);
        table.put("%", TokenType.SLASH_SYMBOL);
        table.put("*", TokenType.STAR_SYMBOL);
        table.put("\"", TokenType.DOUBLE_QUOTATION);
        table.put("'", TokenType.SINGLE_QUOTATION);
        table.put("println", TokenType.PRINTLN);
        //make sure we have all token types added, especially identifiers/value_literals
        tokens.forEach(token -> table.put(token.getValue(), token.getType()));
    }

    public boolean contains(Token token){
        return table.containsKey(token.getValue());
    }

    public TokenType getType(String value){
        return table.get(value);
    }

}
