package com.hai.javase.sort.each.model;

/**
 * Created by Administrator on 2018/2/9.
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int value) {
        this.val = value;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node(int value, Node left, Node right) {
        this(left, right);
        this.val = value;
    }
}
