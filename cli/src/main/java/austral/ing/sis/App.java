package austral.ing.sis;

import edu.austral.ingsis.Lexer;
import lombok.val;
import picocli.CommandLine.*;

import java.util.List;
import java.util.concurrent.Callable;

@Command(
        name = "printscript",
        description = "Executes printscript files",
        version = "1.0",
        mixinStandardHelpOptions = true
)
public class App implements Callable<Integer> {

    @Parameters(description = "File to read", arity = "1")
    private String filePath;

    private Lexer lexer = new Lexer();

    private void run() {
//
//        List<String> document = FileReaderPS.read(filePath);
//        val tokens = lexer.tokenize(document);
//        val nodes = .parse(tokens);
//
//        // here validates
//        if (validateOnly) ValidatorImpl.run(nodes);
//        else InterpreterImpl.run(nodes);
        //    ValidatorImpl.run(nodes);
        //    if (validateOnly) return;
        //    InterpreterImpl.run(nodes);
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
