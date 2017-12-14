package com.hai.ijavase.desinpattern.factory;

/**
 * Created by Administrator on 2017/10/7.
 */
public class FactoryWithInterface {
    public static void main(String[] args) {
        IWorkFactory studentFactory = new StudentFactory();
        studentFactory.getWork().doWork();

        IWorkFactory teacherFactory = new TeacherFactory();
        teacherFactory.getWork().doWork();
    }
}


interface IWorkFactory {
    IWork getWork();
}

class StudentFactory implements IWorkFactory {
    @Override
    public IWork getWork() {
        return new StudentWork();
    }
}

class TeacherFactory implements IWorkFactory {
    @Override
    public IWork getWork() {
        return new TeacherWork();
    }
}

interface IWork {
    void doWork();
}

class StudentWork implements IWork {
    @Override
    public void doWork() {
        System.out.println(this.getClass().getName() + ".doWork...");
    }
}

class TeacherWork implements IWork {
    @Override
    public void doWork() {
        System.out.println(this.getClass().getName() + ".doWork...");
    }
}