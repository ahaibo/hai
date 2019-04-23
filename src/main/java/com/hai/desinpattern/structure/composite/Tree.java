package com.hai.desinpattern.structure.composite;

//组合模式使用场景：将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等。
public class Tree {
    private TreeNode root;

    public Tree(Object data) {
        this.root = new TreeNode(data);
    }

    public static void main(String[] args) {
        Tree tree = new Tree("root");

        TreeNode node1 = new TreeNode("node1");
        TreeNode node2 = new TreeNode("node2");
        TreeNode node3 = new TreeNode("node3");
        TreeNode node4 = new TreeNode("node4");
        TreeNode node5 = new TreeNode("node5");
        TreeNode node6 = new TreeNode("node6");

        node1.add(node2).add(node3);
        node3.add(node4).add(node5);

        tree.root.add(node1).add(node6);

        tree.root.displayTreeNodes();
    }
}
