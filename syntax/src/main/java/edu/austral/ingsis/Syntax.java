package edu.austral.ingsis;

import edu.austral.ingsis.tokens.SyntaxToken;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Syntax {

    private final SyntaxTable table = new SyntaxTable();

    public List<SyntaxToken> categorize(List<Token> tokens){
        final List<SyntaxToken> syntaxTokens = new ArrayList<>();

        for(int i = 0; i < tokens.size(); i++){
            if(!table.contains(tokens.get(i))){
                final SyntaxToken previous = syntaxTokens.get(i - 1);
                if(previous.getType() == TokenType.LET){
                    syntaxTokens.add(table.truncate(tokens.get(i), TokenType.IDENTIFIER));
                }else if(previous.getType() == TokenType.DOUBLE_QUOTATION || previous.getType() == TokenType.SINGLE_QUOTATION){
                    syntaxTokens.add(table.truncate(tokens.get(i), TokenType.STRING_LITERAL));
                }
            }else{
                syntaxTokens.add(table.classify(tokens.get(i)));
            }
        }

        return syntaxTokens;
    }
}
