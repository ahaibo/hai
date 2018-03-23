package com.hai.javase.sort.each;

import com.hai.javase.sort.each.model.Node;

import java.util.Stack;

/**
 * 二叉树遍历算法之二：中序遍历
 * <p>
 * 中序遍历遍历指的是先访问二叉树中节点的左孩子，再访问当前节点，最后再访问其右孩子。具体访问步骤如下：
 * <p>
 * 1.首先访问根节点，判断根节点是否有左孩子，如果有，进行第二步；如果没有，跳到第三步；
 * 2.访问左孩子，继续判断当前节点是否有左孩子，如果有则继续访问其左孩子，直到某节点的左孩子为空
 * 3.判断是否有右孩子，如果有，则继续判断当前节点是否有左孩子，一直到某节点没有左孩子为止
 * 4.把第二步访问的节点做为当前节点（该节点无左孩子，如图中的15节点），按照规则，下一步应该访问其右孩子
 * 5.返回到第四部节点的双亲节点（15的双亲节点是5），并设为当前访问的节点，下一步访问的是其右孩子（这里5没有右孩子）
 * 6.继续访问第五步访问节点的双亲节点（5的双亲节点是6），下一步仍然访问其右孩子。
 * 7.左子树访问完毕，继续第三步中右子树的访问，步骤与上面一直，最终每个节点都将访问一遍
 */
public class TravelTree {

    //中序遍历的递归实现
    public void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.left);
        System.out.print(node.val + " ");
        inOrderTraverse(node.right);
    }

    //中序遍历的非递归实现
    public void inOrderTraverse2(Node node) {
        Stack<Node> s = new Stack<Node>();
        while (node != null || !s.isEmpty()) {
            //遍历左子树
            while (node != null) {
                s.push(node);
                node = node.left;
            }
            //遍历右子树
            if (!s.isEmpty()) {
                node = s.pop();
                System.out.print(node.val + " ");
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

        new TravelTree().inOrderTraverse(root);
    }
}