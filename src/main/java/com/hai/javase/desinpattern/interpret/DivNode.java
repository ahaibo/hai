package com.hai.javase.desinpattern.interpret;

public class DivNode extends SymbolNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return super.left.interpret() / super.right.interpret();
    }
}  