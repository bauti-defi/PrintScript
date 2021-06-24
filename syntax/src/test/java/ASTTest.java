import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.nodes.DeclarationAssignationNode;
import edu.austral.ingsis.tokens.Token;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ASTTest implements TokenHelper {

  @Test
  public void testAstBuild() {
    // let x:number = "hola";
    List<Token> tokens =
        Arrays.asList(
            createMockToken("let", TokenType.LET),
            createMockToken("x", TokenType.IDENTIFIER),
            createMockToken(":", TokenType.COLON),
            createMockToken("number", TokenType.TYPE),
            createMockToken("=", TokenType.EQUALS),
            createMockToken("5", TokenType.LITERAL),
            createMockToken(";", TokenType.SEMICOLON));

    final AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_0);

    assertEquals(1, ast.getBlock().getNodes().size());
    boolean isRootAssignationNode =
        ast.getBlock().getNodes().get(0) instanceof DeclarationAssignationNode;
    assertEquals(true, isRootAssignationNode);
    final DeclarationAssignationNode declarationAssignationNode =
        (DeclarationAssignationNode) ast.getBlock().getNodes().get(0);
    assertEquals(TokenType.EQUALS, declarationAssignationNode.getToken().getType());
    assertEquals(TokenType.LET, declarationAssignationNode.getLeft().getToken().getType());
    assertEquals(TokenType.LITERAL, declarationAssignationNode.getRight().getToken().getType());
  }

  @Test
  public void test01(){
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/resources/test4.txt"));

    final AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
    assertTrue(ast.getBlock().getNodes().size() > 0);
  }

  @Test
  public void test02(){
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/resources/test2.txt"));

    final AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
    assertTrue(ast.getBlock().getNodes().size() > 0);
  }
}
