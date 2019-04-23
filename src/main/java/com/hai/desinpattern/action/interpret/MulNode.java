package com.hai.desinpattern.action.interpret;

public class MulNode extends SymbolNode {
    public MulNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        this.before("interpret by mul:", "*");
        return left.interpret() * right.interpret();
    }
}  