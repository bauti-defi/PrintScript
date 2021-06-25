import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.ast.nodes.PrintlnNode;
import edu.austral.ingsis.interpreter.ASTVisitor;
import edu.austral.ingsis.interpreter.Context;
import edu.austral.ingsis.interpreter.Interpreter;
import edu.austral.ingsis.tokens.Token;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InterpretPrintTest {

  private AST createAST(String filename, String version) {
    Lexer lexer = new Lexer(version);
    List<Token> tokens = lexer.lex(FileReaderPS.read("src/test/java/scripts/" + filename));
    return AST.create(
        tokens,
        version.equals("1.1")
            ? GlobalASTConfig.NODE_PARSERS_V_1_1
            : GlobalASTConfig.NODE_PARSERS_V_1_0);
  }

  @Test
  public void test01() {

    final AST ast = createAST("testPrint.txt", "1.0");
    final Context context = new Context();

    ASTVisitor.create(context, Interpreter.DEFAULT_OUT)
        .visit((PrintlnNode) ast.getBlock().getNodes().get(0));
  }

  @Test
  public void test02() {
    ASTVisitor.create(null, null);
  }
}
