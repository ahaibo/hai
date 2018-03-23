package com.hai.javase.sort.each;

/**
 * 图的深度优先遍历算法
 * <p>
 * 首先需要定义一个存储图的数据结构，在Java中可以使用邻接表来实现图存储。
 * 具体而言就是，图的顶点用一维数组存储，每个顶点的邻接顶点用一个单链表进行存储。
 */
public class DepthFirstSearch {

    //创建一个标记数组
    private boolean[] marked;
    //访问计数器
    private int count;

    /**
     * 构造一幅图并进行深度优先遍历
     * <p>Description: </p>
     *
     * @param G 读入的图
     * @param s 开始遍历的顶点
     */
    public DepthFirstSearch(MyGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(MyGraph G, int s) {
        marked[s] = true;
        count++;
        System.out.print(s + " ");
        for (int w : G.adj(s)) {
            //如果没有被访问过就继续遍历
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean[] getMarked() {
        return marked;
    }

    public int getCount() {
        return count;
    }
}