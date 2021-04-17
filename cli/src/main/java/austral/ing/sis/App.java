package austral.ing.sis;

import edu.austral.ingsis.Lexer;
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

  private Lexer lexer = new Lexer();

  private void run() {}

  @Override
  public Integer call() throws Exception {
    return null;
  }
}
