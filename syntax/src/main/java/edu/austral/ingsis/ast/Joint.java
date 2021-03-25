package edu.austral.ingsis.ast;

public interface Joint<T extends Node, K extends Node> extends Node{

    void setLeft(T node);

    T getLeft();

    void setRight(K node);

    K getRight();
}
