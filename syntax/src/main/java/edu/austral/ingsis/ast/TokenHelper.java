package edu.austral.ingsis.ast;

import java.util.List;

public interface TokenHelper {

    default boolean isTokenType(Token token, TokenType type){
        return token.getType() == type;
    }

    default boolean containsToken(List<Token> token, TokenType condition){
        return token.stream().anyMatch(t -> t.getType() == condition);
    }

    default boolean startsWith(List<Token> tokens, TokenType type){
        return tokens.get(0).getType() == type;
    }

    default boolean endsWith(List<Token> tokens, TokenType type){
        return tokens.get(tokens.size() - 1).getType() == type;
    }

    default int getIndexOfToken(List<Token> line, TokenType type){
        int index;
        for(index = 0; index < line.size(); index++){
            if(line.get(index).getType() == type){
                return index;
            }
        }
        return -1;
    }
}
