package com.hai.javase.datastructure.tree;

import org.apache.poi.ss.formula.functions.T;

import java.util.LinkedList;

/**
 * 将一个数组转换为二叉树并遍历
 * Created by Administrator on 2018/6/2.
 */
public class BinTreeTraverse {

    private LinkedList<Node> nodeList;

    class Node<T> {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
        }
    }

    public <T> void convertToBinTree(T[] array) {
        if (null == array || array.length == 0) {
            return;
        }
        int length = array.length;
        nodeList = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            nodeList.add(new Node(array[i]));
        }

        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).left = nodeList.get(parentIndex * 2 + 1);
            nodeList.get(parentIndex).right = nodeList.get(parentIndex * 2 + 2);
        }

        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = length / 2 - 1;
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (length % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }
    }

    public void preOrderTraverse(Node<T> node) {
        if (checkNode(node)) {
            System.out.print(node.data + "\t");
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    public void middleOrderTraverse(Node<T> node) {
        if (checkNode(node)) {
            middleOrderTraverse(node.left);
            System.out.print(node.data + "\t");
            middleOrderTraverse(node.right);
        }
    }

    public void postOrderTraverse(Node<T> node) {
        if (checkNode(node)) {
            postOrderTraverse(node.left);
            postOrderTraverse(node.right);
            System.out.print(node.data + "\t");
        }
    }

    private boolean checkNode(Node node) {
        return null != node;
    }

    public static void main(String[] args) {
//        Object[] array = {1, 2, 3, "a", 4, "b", "c", 5};
        Object[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinTreeTraverse binTree = new BinTreeTraverse();
        binTree.convertToBinTree(array);
        // nodeList中第0个索引处的值即为根节点
        Node root = binTree.nodeList.get(0);

        System.out.println("先序遍历：");
        binTree.preOrderTraverse(root);
        System.out.println();

        System.out.println("中序遍历：");
        binTree.middleOrderTraverse(root);
        System.out.println();

        System.out.println("后序遍历：");
        binTree.postOrderTraverse(root);
    }

}
