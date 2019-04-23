package com.hai.desinpattern.structure.composite;

import com.alibaba.fastjson.JSONObject;

import java.util.Enumeration;
import java.util.Vector;

public class TreeNode {
    private Object data;
    private TreeNode parent;
    private Vector<TreeNode> children;

    public TreeNode(Object data) {
        this.data = data;
        this.children = new Vector<>();
    }

    public TreeNode add(TreeNode node) {
        this.children.add(node);
        return this;
    }

    public boolean remove(TreeNode node) {
        return this.children.remove(node);
    }

    public Enumeration<TreeNode> getChildrenNodes() {
        return this.children.elements();
    }

    public TreeNode displayTreeNodes() {
        System.out.println(JSONObject.toJSONString(this));
        return this;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public Vector<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Vector<TreeNode> children) {
        this.children = children;
    }
}
