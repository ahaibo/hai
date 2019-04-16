package com.hai.javase.datastructure.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 一致性hash算法
 */
public class ConsistentHash<T> {

    //复制的(虚拟)节点个数
    private final int numberOfReplicas;

    private final SortedMap<Long, T> circle = new TreeMap<>();

    private HashFunc hashFunc;


    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this(numberOfReplicas, nodes, new HashFunc() {
            @Override
            public Long hash(Object key) {
                return md5HashingAlg(key.toString());
            }
        });
    }

    public ConsistentHash(int numberOfReplicas, Collection<T> nodes, HashFunc hashFunc) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunc = hashFunc;
        addNodes(nodes);
    }

    /**
     * 添加节点
     *
     * @param nodes
     */
    public void addNodes(Collection<T> nodes) {
        for (T node : nodes) {
            addNode(node);
        }
    }

    /**
     * 添加节点，实际为每个节点添加 numberOfReplicas 个副本(虚拟)节点
     *
     * @param node
     */
    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            this.circle.put(this.hashFunc.hash(node.toString() + i), node);
        }
    }

    /**
     * 删除所有虚拟节点
     *
     * @param node
     * @return
     */
    public List<T> remove(T node) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < numberOfReplicas; i++) {
            result.add(this.circle.remove(this.hashFunc.hash(node.toString() + i)));
        }
        return result;
    }

    public T get(Object key) {
        if (this.circle.isEmpty()) {
            return null;
        }

        Long hash = this.hashFunc.hash(key);
        if (!this.circle.containsKey(hash)) {
            //返回此映射的部分视图，其键大于等于 hash TODO
            SortedMap<Long, T> tailMap = this.circle.tailMap(hash);
            hash = tailMap.isEmpty() ? this.circle.firstKey() : tailMap.firstKey();
        }

        return this.circle.get(hash);
    }

    /**
     * Hash算法对象，用于自定义hash算法
     */
    public interface HashFunc {
        /**
         * 自定义hash算法
         *
         * @param key
         * @return
         */
        Long hash(Object key);
    }

    /**
     * MD5算法
     *
     * @param key
     * @return
     */
    public static Long md5HashingAlg(String key) {
        Long result = 0L;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            result = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8)
                    | (long) (bKey[0] & 0xFF);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用FNV1hash算法
     *
     * @param key
     * @return
     */
    public static long fnv1HashingAlg(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
}
