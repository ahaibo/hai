package com.hai.desinpattern.action.interpret;

public class ModNode extends SymbolNode {
    public ModNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        this.before("interpret by mod:", "%");
        return super.left.interpret() % super.right.interpret();
    }
}  