package austral.ing.sis;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import java.util.List;
import java.util.concurrent.Callable;
import picocli.CommandLine.*;

@Command(
    name = "printscript",
    description = "Executes printscript files",
    version = "1.0",
    mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {

  @Parameters(description = "File to read", arity = "1")
  private String filePath;

  private final Lexer lexer = new Lexer();

  private void run() {
    List<String> document = FileReaderPS.read(filePath);
    List<Token> tokens = lexer.tokenize(document);
  }

  @Override
  public Integer call() throws Exception {
    return null;
  }
}
