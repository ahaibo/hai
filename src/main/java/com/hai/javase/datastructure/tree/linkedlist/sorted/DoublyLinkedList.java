package com.hai.javase.datastructure.tree.linkedlist.sorted;

import com.hai.javase.sort.search.FibonacciSearch;

/**
 * 双向链表
 */
public class DoublyLinkedList {

    private Node first;
    private Node last;

    public DoublyLinkedList() {
        this.first = first;
        this.last = last;
    }

    public boolean isEmpty() {
        return null == first;
    }

    public void insertFirst(long data) {
        Node node = Node.newInstance(data);
        if (this.isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        node.next = first;
        first = node;
    }

    public void insertLast(long data) {
        Node node = Node.newInstance(data);
        if (this.isEmpty()) {
            first = node;
        } else {
            last.next = node;
        }
        node.previous = last;
        last = node;
    }

    public boolean insertAfter(long key, long data) {
        if (this.isEmpty()) {
            return false;
        }

        Node current = first;
        while (current.data != key) {
            current = current.next;
            if (null == current) {
                return false;
            }
        }

        Node node = Node.newInstance(data);
        if (current == last) {
            node.next = null;
            last = node;
        } else {
            current.next.previous = node;
            node.next = current.next;
        }
        node.previous = current;
        current.next = node;

        return true;
    }

    public Node deleteNode(long data) {
        if (this.isEmpty()) {
            return null;
        }

        Node current = first;
        while (current.data != data) {
            current = current.next;
            if (null == current) {
                return null;
            }
        }

        if (current == first) {
            first = current.next;
            current.next.previous = null;
        } else {
            current.previous.next = current.next;
        }

        if (current == last) {
            last = current.previous;
            current.previous.next = null;
        } else {
            current.next.previous = current.previous;
        }

        return current;
    }

    public String display() {
        return display(true);
    }

    public String display(boolean forward) {
        if (this.isEmpty()) {
            return "";
        }

        StringBuilder out = new StringBuilder();
        Node node = forward ? first : last;
        while (node != null) {
            out.append(node.data).append("\t");
            node = forward ? node.next : node.previous;
        }

        return out.length() > 0 ? out.substring(0, out.length() - 1) : "";
    }

    public Node deleteFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public Node deleteLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    static class Node {
        public long data;
        public Node previous;
        public Node next;

        public Node(long data) {
            this.data = data;
        }

        public static Node newInstance(long data) {
            return new Node(data);
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        doublyLinkedList.insertFirst(11);
        doublyLinkedList.insertFirst(22);
        doublyLinkedList.insertFirst(33);

        doublyLinkedList.insertLast(66);
        doublyLinkedList.insertLast(77);

        doublyLinkedList.insertAfter(33, 44);
        doublyLinkedList.insertAfter(44, 55);

        System.out.println(doublyLinkedList.display());
        System.out.println(doublyLinkedList.display(false));

        doublyLinkedList.deleteNode(55);
        System.out.println(doublyLinkedList.display());
        System.out.println(doublyLinkedList.display(false));

        doublyLinkedList.deleteFirst();
        doublyLinkedList.deleteLast();
        System.out.println(doublyLinkedList.display());
        System.out.println(doublyLinkedList.display(false));
    }
}
