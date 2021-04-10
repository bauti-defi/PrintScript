package edu.austral.ingsis.ast;

import java.util.List;
import java.util.Stack;

public class TokenPattern {

    final Node tail;

    private TokenPattern(Node tail) {
        this.tail = tail;
    }

    public boolean matches(List<Token> tokens){
        final Stack<Node> nodes = asStack(tail);

        if(tokens.size() != nodes.size()){
            return false;
        }

        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i).getType() != nodes.pop().token()){
                return false;
            }
        }

        return true;
    }

    public boolean startWith(List<Token> tokens){
        final Stack<Node> nodes = asStack(tail);

        for(int i = 0; i < tokens.size(); i++){
            if(nodes.isEmpty()){
                break;
            }else if(tokens.get(i).getType() != nodes.pop().token()){
                return false;
            }
        }

        return true;
    }

    private final Stack<Node> asStack(Node tail){
        final Stack<Node> nodes = new Stack<>();
        Node node = tail;
        nodes.push(node);
        while((node = node.next) != null){
            nodes.push(node);
        }

        return nodes;
    }

    static class Node{
        Node next;
        final TokenType type;

        Node(TokenType type){
            this.type = type;
        }

        Node(Node next, TokenType type) {
            this(type);
            this.next = next;
        }

        public void setNext(Node node){
            this.next = node;
        }

        public Node next(){
            return this.next;
        }

        public TokenType token() {
            return this.type;
        }
    }

    public static class Builder{

        Node next;

        private Builder(TokenType type){
            this.next = new Node(type);
        }

        public TokenPattern end(){
            return new TokenPattern(next);
        }

        private Builder next(TokenType type){
            this.next = new Node(this.next, type);
            return this;
        }

        public Builder let(){
            return next(TokenType.LET);
        }

        public Builder identifier(){
            return next(TokenType.IDENTIFIER);
        }

        public Builder colon(){
            return next(TokenType.COLON);
        }

        public Builder type(){
            return next(TokenType.TYPE);
        }

        public Builder equals(){
            return next(TokenType.EQUALS);
        }

        public Builder reference(){
            return next(TokenType.IDENTIFIER);
        }

        public Builder literal(){
            return next(TokenType.LITERAL);
        }

        public Builder plus(){
            return next(TokenType.PLUS_SYMBOL);
        }

        public Builder semicolon(){
            return next(TokenType.SEMICOLON);
        }

        public Builder minus(){
            return next(TokenType.MINUS_SYMBOL);
        }

        public Builder star(){
            return next(TokenType.STAR_SYMBOL);
        }

        public Builder slash(){
            return next(TokenType.SLASH_SYMBOL);
        }

        public static Builder of(TokenType type){
            return new Builder(type);
        }

    }

}
