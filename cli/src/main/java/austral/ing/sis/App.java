package austral.ing.sis;

import austral.ingsis.FileReaderPS;
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

  private void run() {
//      FileReaderPS fileReaderPS =
//
//            List<String> document = austra.ingsis.FileReaderPS.read(filePath);
//            val tokens = lexer.tokenize(document);
//            val nodes = .parse(tokens);
//
//            // here validates
//            if (validateOnly) ValidatorImpl.run(nodes);
//            else InterpreterImpl.run(nodes);
//        ValidatorImpl.run(nodes);
//        if (validateOnly) return;
//        InterpreterImpl.run(nodes);
  }

  @Override
  public Integer call() throws Exception {
    return null;
  }
}
