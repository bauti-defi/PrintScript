package edu.austral.ingsis.ast.parsers;

import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.ASTBuilder;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.exceptions.SyntaxException;
import edu.austral.ingsis.ast.nodes.ExpressionNode;
import edu.austral.ingsis.ast.nodes.IfStatementNode;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;

import java.util.List;

public class IfStatementParser implements NodeParser<IfStatementNode>{

    private final ExpressionParser expressionParser = new ExpressionParser();

    @Override
    public boolean predicate(List<Token> tokens) {
        return true;
    }

    @Override
    public IfStatementNode parse(List<Token> tokens) {
        int leftParenthesesIndex = getIndexOfToken(tokens, TokenType.L_PARENTHESES);
        int rightParenthesesIndex = getIndexOfToken(tokens, TokenType.R_PARENTHESES);

        if (leftParenthesesIndex == -1 || rightParenthesesIndex == -1) {
            throw new SyntaxException();
        }

        final IfStatementNode ifStatementNode = new IfStatementNode(tokens.get(0));
        final ExpressionNode expressionNode =
                expressionParser.parse(tokens.subList(leftParenthesesIndex + 1, rightParenthesesIndex));
        ifStatementNode.setLeft(expressionNode);

        int openingCurlyBrace = getIndexOfToken(tokens, TokenType.L_CURLY_BRACE);
        int closingCurlyBrace = getIndexOfToken(tokens, TokenType.R_CURLY_BRACE);

        if (openingCurlyBrace == -1 || closingCurlyBrace == -1) {
            throw new SyntaxException();
        }

        List<Token> ifBlock = tokens.subList(openingCurlyBrace + 1, closingCurlyBrace);

        AST ifbody = AST.create(ifBlock, GlobalASTConfig.NODE_PARSERS_V_1_1);
        ifStatementNode.setIfBlock(ifbody);

        int elseIndex = getIndexOfToken(tokens, TokenType.ELSE);

        if(elseIndex == -1){
            return ifStatementNode;
        } else if(elseIndex != closingCurlyBrace + 1){
            throw new SyntaxException();
        }

        List<Token> elseBlock = tokens.subList(elseIndex + 1, tokens.size() - 1);

        openingCurlyBrace = getIndexOfToken(elseBlock, TokenType.L_CURLY_BRACE);
        closingCurlyBrace = getIndexOfToken(elseBlock, TokenType.R_CURLY_BRACE);
        AST elseBody = AST.create(elseBlock.subList(openingCurlyBrace + 1, closingCurlyBrace), GlobalASTConfig.NODE_PARSERS_V_1_1);
        ifStatementNode.setElseBlock(elseBody);

        return ifStatementNode;
    }
}
