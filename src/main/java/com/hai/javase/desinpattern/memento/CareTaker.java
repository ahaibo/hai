package com.hai.javase.desinpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责人。负责保存好备忘录，不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其他对象。
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}