package com.hai.ijavase.arithmetic;

/**
 * 请用java写二叉树算法，实现添加数据形成二叉树功能，并以先序的方式打印出来<br>
 * 问题来自java面试题目，实现摘自网上提供
 * 
 * @author Administrator
 * 
 */
public class Tree {

	private int data;// 数据节点
	private Tree left;// 左子树
	private Tree right;// 右子树

	public Tree(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	/**
	 * 创建二叉树，返回根结点
	 * 
	 * @param input
	 * @return
	 */
	public static Tree createTree(int[] input) {
		Tree root = null;
		Tree temp = null;
		for (int i = 0; i < input.length; i++) {
			// 创建根节点
			if (root == null) {
				root = temp = new Tree(input[i]);
			} else {
				// 回到根结点
				temp = root;
				// 添加节点
				while (temp.data != input[i]) {
					if (input[i] <= temp.data) {
						if (temp.left != null) {
							temp = temp.left;
						} else {
							temp.left = new Tree(input[i]);
						}
					} else {
						if (temp.right != null) {
							temp = temp.right;
						} else {
							temp.right = new Tree(input[i]);
						}
					}
				}
			}
		}
		return root;
	}

	/**
	 * 前序遍历
	 * 
	 * @param tree
	 */
	public static void preOrder(Tree tree) {
		if (tree != null) {
			System.out.print(tree.data + " ");
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param tree
	 */
	public static void midOrder(Tree tree) {
		if (tree != null) {
			midOrder(tree.left);
			System.out.print(tree.data + " ");
			midOrder(tree.right);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param tree
	 */
	public static void posOrder(Tree tree) {
		if (tree != null) {
			posOrder(tree.left);
			posOrder(tree.right);
			System.out.print(tree.data + " ");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] input = { 4, 2, 6, 1, 3, 5, 7 };
		Tree tree = createTree(input);
		System.out.print("前序遍历：");
		preOrder(tree);
		System.out.print("\n中序遍历：");
		midOrder(tree);
		System.out.print("\n后序遍历：");
		posOrder(tree);
	}
}