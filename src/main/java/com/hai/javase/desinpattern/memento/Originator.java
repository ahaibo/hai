package com.hai.javase.desinpattern.memento;

/**
 * 原发器：
 * 负责创建一个备忘录，用以记录当前对象的内部状态，通过也可以使用它来利用备忘录恢复内部状态。
 * 同时原发器还可以根据需要决定 Memento 存储 Originator 的那些内部状态。
 *
 * Created by Administrator on 2018/2/9.
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
