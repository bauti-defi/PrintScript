package austral.ing.sis;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import edu.austral.ingsis.ast.AST;
import java.util.List;

import edu.austral.ingsis.interpreter.Interpreter;
import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(
    name = "printscript",
    description = "Executes printscript files",
    version = "1.0",
    mixinStandardHelpOptions = true)
public class App implements Runnable {
  // CLI
  @Parameters(paramLabel = "<file path>", description = "Path of .txt file")
  private String filePath = "";

  private final Lexer lexer = Lexer.builder().build();
  private final Interpreter interpreter = new Interpreter();
  public void run() {
    List<String> document = FileReaderPS.read(filePath);
    List<Token> tokens = lexer.tokenize(document);
    AST ast = AST.create(tokens);
    interpreter.execute(ast);
  }

  public static void main(String[] args) {
    final var exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}
