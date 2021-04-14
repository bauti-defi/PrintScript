package edu.austral.ingsis.ast.visitor;

public interface Visitable {

    void accept(Visitor visitor);
}
