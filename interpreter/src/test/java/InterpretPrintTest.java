import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.nodes.PrintlnNode;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import edu.austral.ingsis.tokens.Token;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InterpretPrintTest {

  private AST createAST(String filename) {
    Lexer lexer = new Lexer();
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/scripts/" + filename));
    tokens.forEach(System.out::println);
    return AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
  }

  @Test
  public void test01() {

    final AST ast = createAST("testPrint.txt");
    final Context context = new Context();

    ASTVisitor.create(context).visit((PrintlnNode) ast.getBlock().getNodes().get(0));
  }
}
