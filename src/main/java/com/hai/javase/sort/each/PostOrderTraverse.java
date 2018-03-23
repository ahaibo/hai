package com.hai.javase.sort.each;

import com.hai.javase.sort.each.model.Node;

import java.util.Stack;

/**
 * 二叉树遍历算法之三：后序遍历
 * <p>
 * 后续遍历的递归实现
 * <p>
 * 后续遍历指的是先访问节点的左右孩子，最后访问节点本身。所以使用后序遍历得到的结果的最后一个节点就是根节点。采用后续遍历的具体步骤如下：
 * <p>
 * 1.先访问根节点，如果有左孩子，进入第二步；如果有右孩子，进入第三步
 * 2.对左孩子继续判断其是否有左孩子，直到某节点的左孩子为空，设为cur节点
 * 3.对右孩子继续判其是否有左孩子，直到某个节点的左孩子为空，设为curR节点
 * 4.cur节点访问之后，访问其双亲节点的右孩子，如果其双亲节点的右孩子不为空的话。接着访问cur节点的双亲节点，设为curP节点。
 * 5.cur节点的双亲节点访问结束之后，继续访问curP节点的右孩子（如果不为空的话），接着访问curP节点本身
 * 6.重复以上过程直到根节点
 * 7.继续访问根节点的右孩子，回到第三步，访问curR节点之后，访问其双亲节点的右孩子（如果有的话）
 * 8.最后访问curR的双亲节点本身
 * 9.重复以上过程直到访问根节点，访问结束
 * <p>
 * Created by Administrator on 2018/2/9.
 */
public class PostOrderTraverse {

    public void postOrderTraverse(Node node) {
        if (node == null) return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.val + " ");
    }

    /**
     * 后续遍历的非递归实现
     * <p>
     * 后续遍历的非递归实现与前面的前序遍历和中序遍历的方式有一些不同，
     * 由于根节点是最后访问的，在访问的时候创建一个栈保存遍历的结果还不够，所以需要使用一个辅助栈来记住每个访问节点的双亲节点。
     *
     * @param node
     */
    public void postOrderTraverse2(Node node) {
        Stack<Node> s1 = new Stack<Node>();
        //创建栈s2的目的在于记住每个访问的节点
        Stack<Integer> s2 = new Stack<Integer>();
        //如果栈s2的栈顶是1，标识当前访问的节点
        Integer i = new Integer(1);
        while (node != null || !s1.isEmpty()) {
            while (node != null) {
                s1.push(node);
                s2.push(0);
                node = node.left;
            }
            //这个循坏的目的是对栈s2栈顶为1时对应的栈s1的栈顶元素进行访问
            while (!s1.isEmpty() && s2.peek().equals(i)) {
                s2.pop();
                System.out.print(s1.pop().val + " ");
            }
            //访问左子树到头后，就可以访问其右孩子了
            if (!s1.isEmpty()) {
                s2.pop();
                s2.push(i);
                node = s1.peek();
                node = node.right;
            }
        }
    }
}
