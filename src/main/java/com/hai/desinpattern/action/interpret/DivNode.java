package com.hai.desinpattern.action.interpret;

public class DivNode extends SymbolNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        this.before("interpret by div:", "/");
        return super.left.interpret() / super.right.interpret();
    }
}  