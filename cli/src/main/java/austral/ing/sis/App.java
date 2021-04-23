package austral.ing.sis;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.Token;
import java.util.List;
import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(
    name = "printscript",
    description = "Executes printscript files",
    version = "1.0",
    mixinStandardHelpOptions = true)
public class App implements Runnable {
    //CLI
    @Parameters(paramLabel = "<file path>", description = "Path of .txt file")
    private String filePath = "";

    private final Lexer lexer = Lexer.builder().build();

    public void run() {
        List<String> document = FileReaderPS.read(filePath);
        List<Token> tokens = lexer.tokenize(document);
        System.out.println(tokens);
    }

    public static void main(String[] args) {
        final var exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
