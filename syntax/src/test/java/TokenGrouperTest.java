import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.TokenGrouper;
import edu.austral.ingsis.tokens.Token;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TokenGrouperTest implements TokenHelper{


    @Test
    public void test01(){
        List<Token> tokens = readTokensFromFile("test01-grouper.txt");

        List<List<Token>> groups = TokenGrouper.group(tokens);

        assertLinesMatch(groups.get(0).stream().map(Token::getValue).collect(Collectors.toList()), Arrays.asList("let", "pi", ":", "number", ";"));

        List<List<String>> expected = Arrays.asList(
                Arrays.asList("pi", "=", "3.14", ";"),
                Arrays.asList("println", "(", "pi", "/", "2", ")", ";")
        );
    }
}