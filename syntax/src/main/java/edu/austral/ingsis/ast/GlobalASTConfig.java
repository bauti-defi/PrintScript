package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.parsers.*;
import edu.austral.ingsis.tokens.TokenType;
import java.util.Arrays;
import java.util.List;

public class GlobalASTConfig {

  public static List<NodeParser<?>> NODE_PARSERS_V_1_0 =
      Arrays.asList(
          new DeclarationAssignationParser(new DeclarationParser(TokenType.LET)),
          new ReferenceAssignationParser(),
          new DeclarationParser(TokenType.LET),
          new PrintlnParser());

  public static List<NodeParser<?>> NODE_PARSERS_V_1_1 =
      Arrays.asList(
          new DeclarationAssignationParser(
              new DeclarationParser(TokenType.LET), new DeclarationParser(TokenType.CONST)),
          new ReferenceAssignationParser(),
          new DeclarationParser(TokenType.LET),
          new DeclarationParser(TokenType.CONST),
          new PrintlnParser());
}
