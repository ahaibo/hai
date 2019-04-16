package com.hai.javase.datastructure.algorithm;

public class NodeReverse {

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public Node reverseNode(Node head) {
        Node p1 = head;
        Node p2 = p1.next;
        Node p3 = null;

        while (p2 != null) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }

        //头尾互置
        head.next = null;
        head = p1;

        return head;
    }

    public class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }


}
