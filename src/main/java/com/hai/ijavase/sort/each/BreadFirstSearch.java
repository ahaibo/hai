package com.hai.ijavase.sort.each;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的广度优先遍历算法
 * <p>
 * 广度优先遍历算法是图的另一种基本遍历算法，其基本思想是尽最大程度辐射能够覆盖的节点，并对其进行访问。
 * <p>
 * 广度优先遍历算法的实现
 * <p>
 * 与深度优先遍历算法相同，都需要一个标记数组来记录一个节点是否被访问过，在深度优先遍历算法中，使用的是一个栈来实现的，
 * 但是广度优先因为需要记录与起点距离最短的节点，或者说能够用尽可能少的边连通的节点，
 * 距离短的优先遍历，距离远的后面再遍历，更像是队列。所以在广度优先遍历算法中，需要使用队列来实现这个过程。
 */
public class BreadFirstSearch {

    //创建一个标记数组
    private boolean[] marked;
    //起点
    private int s;

    public BreadFirstSearch(MyGraph G, int s) {
        marked = new boolean[G.V()];
        this.s = s;
        //开始广度优先搜索
        bfs(G, s);
    }

    private void bfs(MyGraph G, int s2) {
        //创建一个队列
        Queue<Integer> queue = new LinkedList<Integer>();
        //标记起点
        marked[s] = true;
        queue.add(s);
        System.out.print(s + " ");
        while (!queue.isEmpty()) {
            //从队列中删除下一个节点
            int v = queue.poll();
            //将该节点的所有邻接节点加入队列中
            for (int w : G.adj(v)) {
                //如果没有标记就标记
                if (!marked[w]) {
                    marked[w] = true;
                    System.out.print(w + " ");
                    queue.add(w);
                }
            }
        }
    }
}