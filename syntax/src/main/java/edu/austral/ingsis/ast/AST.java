package edu.austral.ingsis.ast;

import edu.austral.ingsis.ast.nodes.CodeBlock;
import edu.austral.ingsis.ast.parsers.NodeParser;
import edu.austral.ingsis.tokens.Token;
import java.util.List;

public class AST {

  private final CodeBlock block;

  private AST(ASTBuilder builder) {
    this.block = new CodeBlock(builder.nodes);
  }

  public CodeBlock getBlock() {
    return block;
  }

  public static AST create(List<Token> tokens, List<NodeParser<?>> parsers) {
    final ASTBuilder builder = new ASTBuilder(parsers);
    builder.process(tokens);
    return new AST(builder);
  }
}
