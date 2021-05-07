// import static org.junit.jupiter.api.Assertions.*;
//
// import edu.austral.ingsis.ast.AST;
// import edu.austral.ingsis.ast.nodes.DeclarationNode;
// import edu.austral.ingsis.ast.nodes.IfStatementNode;
// import edu.austral.ingsis.ast.nodes.PrintlnNode;
// import edu.austral.ingsis.ast.parsers.IfStatementParser;
// import edu.austral.ingsis.tokens.Token;
// import edu.austral.ingsis.tokens.TokenType;
// import java.util.List;
// import org.junit.jupiter.api.Test;
//
// public class IfStatementParserTest implements TokenHelper {
//
//  @Test
//  public void test01() {
//    // if(x){}
//    List<Token> tokens = readTokensFromFile("test01-ifstatement.txt");
//
//    final IfStatementParser parser = new IfStatementParser();
//
//    if (parser.predicate(tokens)) {
//      final IfStatementNode node = parser.parse(tokens);
//      assertEquals(TokenType.IF, node.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, node.getExpression().getToken().getType());
//      assertNotNull(node.getIfBlock());
//      assertNull(node.getElseBlock());
//    } else {
//      throw new Error();
//    }
//  }
//
//  @Test
//  public void test02() {
//    // if(x){}else{}
//    List<Token> tokens = readTokensFromFile("test02-ifstatement.txt");
//
//    final IfStatementParser parser = new IfStatementParser();
//
//    if (parser.predicate(tokens)) {
//      final IfStatementNode node = parser.parse(tokens);
//      assertEquals(TokenType.IF, node.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, node.getExpression().getToken().getType());
//      assertNotNull(node.getIfBlock());
//      assertNotNull(node.getElseBlock());
//    } else {
//      throw new Error();
//    }
//  }
//
//  @Test
//  public void test03() {
//    // if(x){}else{}
//    List<Token> tokens = readTokensFromFile("test03-ifstatement.txt");
//
//    final IfStatementParser parser = new IfStatementParser();
//
//    if (parser.predicate(tokens)) {
//      final IfStatementNode node = parser.parse(tokens);
//      assertEquals(TokenType.IF, node.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, node.getExpression().getToken().getType());
//      assertNotNull(node.getIfBlock());
//      assertNotNull(node.getElseBlock());
//
//      AST elseBlock = node.getElseBlock();
//
//      assertEquals(1, elseBlock.getBlock().getNodes().size());
//
//      PrintlnNode childNode = (PrintlnNode) elseBlock.getBlock().getNodes().get(0);
//      assertEquals(TokenType.PRINTLN, childNode.getToken().getType());
//      assertEquals(TokenType.LITERAL, childNode.getArgs().getToken().getType());
//    } else {
//      throw new Error();
//    }
//  }
//
//  @Test
//  public void test04() {
//    // if(x){}else{}
//    List<Token> tokens = readTokensFromFile("test04-ifstatement.txt");
//
//    final IfStatementParser parser = new IfStatementParser();
//
//    if (parser.predicate(tokens)) {
//      final IfStatementNode node = parser.parse(tokens);
//      assertEquals(TokenType.IF, node.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, node.getExpression().getToken().getType());
//      assertNotNull(node.getIfBlock());
//      assertNotNull(node.getElseBlock());
//
//      AST ifBlock = node.getIfBlock();
//
//      assertEquals(1, ifBlock.getBlock().getNodes().size());
//
//      final DeclarationNode ifChildNode = (DeclarationNode) ifBlock.getBlock().getNodes().get(0);
//      assertEquals(TokenType.CONST, ifChildNode.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, ifChildNode.getLeft().getToken().getType());
//      assertEquals(TokenType.TYPE, ifChildNode.getRight().getToken().getType());
//
//      AST elseBlock = node.getElseBlock();
//
//      assertEquals(1, elseBlock.getBlock().getNodes().size());
//
//      PrintlnNode childNode = (PrintlnNode) elseBlock.getBlock().getNodes().get(0);
//      assertEquals(TokenType.PRINTLN, childNode.getToken().getType());
//      assertEquals(TokenType.LITERAL, childNode.getArgs().getToken().getType());
//    } else {
//      throw new Error();
//    }
//  }
//
//  @Test
//  public void test05() {
//    List<Token> tokens = readTokensFromFile("test05-ifstatement.txt");
//
//    final IfStatementParser parser = new IfStatementParser();
//
//    if (parser.predicate(tokens)) {
//      final IfStatementNode node = parser.parse(tokens);
//      assertEquals(TokenType.IF, node.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, node.getExpression().getToken().getType());
//      assertNotNull(node.getIfBlock());
//      assertNotNull(node.getElseBlock());
//
//      AST ifBlock = node.getIfBlock();
//
//      assertEquals(2, ifBlock.getBlock().getNodes().size());
//
//      final DeclarationNode ifChildNode = (DeclarationNode) ifBlock.getBlock().getNodes().get(0);
//      assertEquals(TokenType.CONST, ifChildNode.getToken().getType());
//      assertEquals(TokenType.IDENTIFIER, ifChildNode.getLeft().getToken().getType());
//      assertEquals(TokenType.TYPE, ifChildNode.getRight().getToken().getType());
//
//      PrintlnNode childNode = (PrintlnNode) ifBlock.getBlock().getNodes().get(1);
//      assertEquals(TokenType.PRINTLN, childNode.getToken().getType());
//      assertEquals(TokenType.LITERAL, childNode.getArgs().getToken().getType());
//    } else {
//      throw new Error();
//    }
//  }
// }
