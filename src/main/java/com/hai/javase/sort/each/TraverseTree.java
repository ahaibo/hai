package com.hai.javase.sort.each;

import com.hai.javase.sort.each.model.Node;

import java.util.Stack;

/**
 * 二叉树遍历算法之一：前序遍历
 * <p>
 * 当调用遍历算法的时候前序遍历的具体过程如下：
 * <p>
 * 首先访问根节点，如果根节点不为空，执行输出语句，打印根节点的值。
 * 如果左子树不为空，则访问根节点的左孩子，并输出根节点做孩子的值
 * 继续访问根节点的左孩子的左孩子，如果不为空则继续输出该左孩子的值；
 * 如果这时左孩子为空，说明该节点是叶子节点，则按照先左孩子后右孩子的访问方式访问其左右孩子，如果不为空就打印输出
 * 左子树访问完毕之后，继续访问根节点的右子树，如果根节点的右孩子不为空，则输出该右孩子
 * 继续访问根节点右孩子的左孩子，如果不为空，则输出
 * 接着访问根节点右孩子的右孩子，如果不为空，则输出
 * Created by Administrator on 2018/2/9.
 */
public class TraverseTree {
    // 前序遍历的递归实现
    // 二叉树的前序遍历是指从根节点出发，按照先根节点，再左子树，后右子树的方法遍历二叉树中的所有节点，使得每个节点都被访问一次。
    public void preOrderTraverse(Node node) {
        if (node == null)
            return;
        // 先根节点
        System.out.println(node.val);
        // 再左孩子
        preOrderTraverse(node.left);
        // 后右孩子
        preOrderTraverse(node.right);
    }

    // 前序遍历的非递归实现
    public void preOrderTraverse2(Node node) {
        if (node == null) return;
        //创建一个栈用于保存遍历的结点
        Stack<Node> s = new Stack<Node>();
        while (node != null || !s.isEmpty()) {
            //遍历左子树
            while (node != null) {
                System.out.print(node.val + " ");
                s.push(node);
                node = node.left;
            }
            //遍历右子树
            if (!s.isEmpty()) {
                node = s.pop();
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        Node node1 = new Node(6);
        Node node2 = new Node(10);
        Node node3 = new Node(5);
        Node node4 = new Node(7);
        Node node5 = new Node(9);
        Node node6 = new Node(11);
        Node node7 = new Node(15);
        Node node8 = new Node(24);
        Node node9 = new Node(30);
        Node node10 = new Node(28);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node7;
        node7.right = node8;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node5.left = node9;
        node6.right = node10;

        TraverseTree t = new TraverseTree();
        t.preOrderTraverse(root);
        t.preOrderTraverse2(root);
    }
}
