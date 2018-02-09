package com.hai.ijavase.sort.each;

import java.util.Iterator;
import java.util.Stack;

/**
 * 深度优先遍历算法的非递归实现方式
 * <p>
 * 使用非递归的方式与递归的思想是一致的，不同的在于需要使用一个栈保存遍历的顶点。
 */
public class NonrecursiveDFS {

    //创建一个标记数组标记访问过的元素
    private boolean[] marked;

    @SuppressWarnings("unchecked")
    public NonrecursiveDFS(MyGraph G, int s) {
        marked = new boolean[G.V()];
        //创建一个迭代器迭代每个顶点的邻接表
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        //获得每个顶点的邻接表
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }

        //创建一个栈用户存放遍历的顶点
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        System.out.print(s + " ");
        stack.add(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            //如果有邻接表的话，就继续遍历
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                //判断是否已被访问过
                if (!marked[w]) {
                    //没访问过就将器标记为已访问过，下次不会再访问了
                    marked[w] = true;
                    System.out.print(w + " ");
                    stack.push(w);
                }
            } else {
                //如果没有邻接表的话就将该顶点弹出栈顶
                stack.pop();
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }


}