package com.hai.desinpattern.action.memento;

/**
 * 备忘录模式：备忘录对象
 * 用于存储 Originator 的内部状态，并且可以防止 Originator 以外的对象访问 Memento。
 * 在备忘录 Memento 中有两个接口，其中 Caretaker 只能看到备忘录中的窄接口，它只能将备忘录传递给其他对象。
 * Originator 可以看到宽接口，允许它访问返回到先前状态的所有数据。
 * Created by Administrator on 2018/2/9.
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
