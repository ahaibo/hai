package com.hai.javase.datastructure.tree.linkedlist.sorted;

import java.util.Random;

/**
 * 链表实现有序列表
 */
public class SortedList {
    private Link first;

    public SortedList() {
    }

    public SortedList(Link[] links) {
        first = null;
        for (Link link : links) {
            insert(link);
        }
    }

    public void insert(Link link) {
        Link previous = null;
        Link current = first;
        while (current != null && link.data > current.data) {
            previous = current;
            current = current.next;
        }
        if (null == previous) {
            first = link;
        } else {
            previous.next = link;
        }
        link.next = current;
    }

    public Link remove() {
        Link temp = first;
        first = null == temp ? null : first.next;
        return temp;
    }

    public Link[] toArray(int size) {
        Link[] array = new Link[size];
        for (int i = 0; i < size; i++) {
            array[i] = this.remove();
        }
        return array;
    }

    /**
     * 展示列表；toArray方法前调用有效
     *
     * @return
     */
    public String display() {
        StringBuilder sb = new StringBuilder();
        Link link;
        boolean hasNext = false;
        do {
            link = this.remove();
            hasNext = null != link;
            if (hasNext) {
                sb.append(link.data).append("\t");
            }
        } while (hasNext);
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    public String display(Link[] array) {
        StringBuilder sb = new StringBuilder();
        for (Link link : array) {
            if (null != link) {
                sb.append(link.data).append("\t");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    static class Link {
        public long data;
        public Link next;

        public Link(long data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int size = 1000, seed = 9999;
        Link[] array = new Link[size];
        Random random = new Random();

        System.out.println("Unsorted array:");
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(seed);
            array[i] = new Link(value);
            System.out.print(value + "\t");
        }

        long start = System.currentTimeMillis();
        SortedList sortedList = new SortedList(array);
        array = sortedList.toArray(size);
        long end = System.currentTimeMillis();

        System.out.println("\nused times: " + (end - start));
        System.out.println("\nSorted array:\n" + sortedList.display(array));

    }
}


