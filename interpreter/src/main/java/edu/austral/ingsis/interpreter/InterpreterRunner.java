package edu.austral.ingsis.interpreter;

import austral.ingsis.FileReaderPS;
import edu.austral.ingsis.Lexer;
import edu.austral.ingsis.ast.AST;
import edu.austral.ingsis.ast.GlobalASTConfig;
import edu.austral.ingsis.tokens.Token;

import java.util.List;
import java.util.function.Consumer;

public class InterpreterRunner {

    public static void run(String filePath, String version, Consumer<String> stdOut) {
        Lexer lexer = new Lexer(version);
        List<String> document = FileReaderPS.read(filePath);
        List<Token> tokens = lexer.lex(document);
        if (version.equals("1.0")) {
            AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_0);
            Interpreter.interpret(ast, stdOut);
        }
        if (version.equals("1.1")) {
            AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
            Interpreter.interpret(ast, stdOut);
        }
    }

}
