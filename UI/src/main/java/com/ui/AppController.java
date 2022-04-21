package com.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.List;

public class AppController {

    // private final Lexer lexer = new Lexer();
    //private final Interpreter interpreter = new Interpreter();

    private String filePath = "";
    //final AST ast = createAST("fullTest01.txt", "1.1");

    //final StdOutTester out = new StdOutTester();

    @FXML
    private Label welcomeText;
    @FXML
    private TextArea texto2;
    @FXML
    private TextField texto1;
    @FXML
    protected void onHelloButtonClick() {
        filePath = texto1.getText();
        texto2.setText(filePath);
//            List<String> document = FileReaderPS.read(filePath);
//            List<Token> tokens = lexer.lex(document);
//            AST ast = AST.create(tokens, GlobalASTConfig.NODE_PARSERS_V_1_1);
//            interpreter.interpret(ast, out);
        //al out le pido la cosa de strings
        //texto2.setText(listadestrings);
    }
}