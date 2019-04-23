package com.hai.desinpattern.action.interpret;

/**
 * 终结表达式抽象类，由于该终结表达式需要解释多个运算符号，同时用来构建抽象语法树
 */
public abstract class SymbolNode implements Node {
    protected Node left;
    protected Node right;
    protected boolean opBefore = true;

    public SymbolNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    protected void before(String prefix, String operation) {
        System.out.println(prefix + " " + this.left.interpret() + " " + operation + " " + this.right.interpret());
    }

    public boolean isOpBefore() {
        return opBefore;
    }

    public void setOpBefore(boolean opBefore) {
        this.opBefore = opBefore;
    }
}