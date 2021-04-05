package edu.austral.ingsis;

import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.HashMap;

public class SyntaxTable {

    private final HashMap<String, TokenType> table = new HashMap<>();

    public SyntaxTable(){
        table.put("let", TokenType.LET);
        table.put("number", TokenType.NUMBER_TYPE);
        table.put(";", TokenType.SEMICOLON);
        table.put(":", TokenType.COLON);
        table.put("=", TokenType.EQUALS);
        table.put("string", TokenType.STRING_TYPE);
        table.put("(", TokenType.L_PARENTHESES);
        table.put(")", TokenType.R_PARANTHESES);
        table.put("+", TokenType.ADDITION_OP);
        table.put("-", TokenType.SUBTRACTION_OP);
        table.put("%", TokenType.DIVISION_OP);
        table.put("*", TokenType.MULTIPLICATION_OP);
        table.put("\"", TokenType.DOUBLE_QUOTATION);
        table.put("'", TokenType.SINGLE_QUOTATION);
        table.put("println", TokenType.PRINTLN);
    }

    public boolean contains(Token token){
        return table.containsKey(token.getValue());
    }

    public Token truncate(Token token, TokenType type){
        table.put(token.getValue(), type);

        return classify(token);
    }

    public Token classify(Token token){
        return new Token() {
            @Override
            public TokenType getType() {
                return table.get(token.getValue());
            }

            @Override
            public String getValue() {
                return token.getValue();
            }

            @Override
            public Integer getLineNumber() {
                return token.getLineNumber();
            }

            @Override
            public Integer getLineIndex() {
                return token.getLineIndex();
            }
        };
    }
}
