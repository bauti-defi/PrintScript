import edu.austral.ingsis.Syntax;
import edu.austral.ingsis.tokens.SyntaxToken;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SyntaxTest {

    private static final Token LetToken = new Token() {
        @Override
        public String getValue() {
            return "let";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 0;
        }
    };

    private static final Token IdentifierToken = new Token() {
        @Override
        public String getValue() {
            return "name";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 4;
        }
    };

    private static final Token ColonToken = new Token() {
        @Override
        public String getValue() {
            return ":";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 8;
        }
    };

    private static final Token StringTypeToken = new Token() {
        @Override
        public String getValue() {
            return "string";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 9;
        }
    };

    private static final Token EqualsToken = new Token() {
        @Override
        public String getValue() {
            return "=";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 16;
        }
    };

    private static final Token LeftQuoteToken = new Token() {
        @Override
        public String getValue() {
            return "'";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 18;
        }
    };

    private static final Token StringLiteralToken = new Token() {
        @Override
        public String getValue() {
            return "bauti";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 19;
        }
    };

    private static final Token RightQuoteToken = new Token() {
        @Override
        public String getValue() {
            return "'";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 24;
        }
    };

    private static final Token SemicolonToken = new Token() {
        @Override
        public String getValue() {
            return ";";
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineIndex() {
            return 25;
        }
    };

    private static final List<Token> tokens = new ArrayList<>();

    static {
        tokens.add(LetToken);
        tokens.add(IdentifierToken);
        tokens.add(ColonToken);
        tokens.add(StringTypeToken);
        tokens.add(EqualsToken);
        tokens.add(LeftQuoteToken);
        tokens.add(StringLiteralToken);
        tokens.add(RightQuoteToken);
        tokens.add(SemicolonToken);
    }

    @Test
    public void testCategorize1(){
        Syntax syntax = new Syntax();
        List<SyntaxToken> syntaxTokens = syntax.categorize(tokens);

        final List<TokenType> expected = Arrays.asList(
                TokenType.LET, TokenType.IDENTIFIER, TokenType.COLON,
                TokenType.STRING_TYPE, TokenType.EQUALS,TokenType.SINGLE_QUOTATION,
                TokenType.STRING_LITERAL, TokenType.SINGLE_QUOTATION, TokenType.SEMICOLON);

        final List<TokenType> result = syntaxTokens.stream().map(SyntaxToken::getType).collect(Collectors.toList());
        assertEquals(expected, result);
    }
}
