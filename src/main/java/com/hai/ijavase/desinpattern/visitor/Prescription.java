package com.hai.ijavase.desinpattern.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ObjectStructure: 对象结构。能够枚举它的元素，可以提供一个高层的接口来允许访问者访问它的元素。
 * Created by Administrator on 2018/2/9.
 */
public class Prescription {
    List<Medicine> list = new ArrayList<Medicine>();

    public void accept(Visitor visitor) {
        Iterator<Medicine> iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }

    public void addMedicine(Medicine medicine) {
        list.add(medicine);
    }

    public void removeMedicien(Medicine medicine) {
        list.remove(medicine);
    }
}
