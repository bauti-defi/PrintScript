import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.Token;
import edu.austral.ingsis.ast.TokenType;
import edu.austral.ingsis.ast.nodes.AssignationNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASTTest implements TokenHelper{

    @Test
    public void testAstBuild(){
        // let x:number = "hola";
        List<Token> tokens = Arrays.asList(
                createMockToken("let", TokenType.LET),
                createMockToken("x", TokenType.IDENTIFIER),
                createMockToken(":", TokenType.COLON),
                createMockToken("number", TokenType.NUMBER_TYPE),
                createMockToken("=", TokenType.EQUALS),
                createMockToken("5", TokenType.NUMBER_LITERAL),
                createMockToken(";", TokenType.SEMICOLON)
        );

        final AST ast = AST.create(tokens);

        assertEquals(1, ast.getNodes().size());
        boolean isRootAssignationNode = ast.getNodes().get(0) instanceof AssignationNode;
        assertEquals(true, isRootAssignationNode);
        final AssignationNode assignationNode = (AssignationNode) ast.getNodes().get(0);
        assertEquals(TokenType.EQUALS, assignationNode.getToken().getType());
        assertEquals(TokenType.COLON, assignationNode.getLeft().getToken().getType());
        assertEquals(TokenType.NUMBER_LITERAL, assignationNode.getRight().getToken().getType());

        assertEquals(assignationNode.getLeft(), ast.getDeclarations().get("x"));
    }
}
