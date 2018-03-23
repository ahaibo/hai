package com.hai.javase.desinpattern.statemode;

/**
 * 状态模式
 * <p>
 * 一、引出状态模式
 * 假设我们现在有一个糖果机项目，那么我们知道正常一般糖果机提供给用户的行为有这么几种：投入硬币、转动曲柄、退出硬币几种行为；
 * 那么糖果机呢一般有这几中状态，待机状态、持有硬币的准备状态、运行状态即正在售出状态和初始状态 这么几种正常状态。 我们发现处于不同状态的时候，持有的行为是不一样的
 * <p>
 * 二、解决办法
 * 为了解决上面的问题，我们首先分析项目中变化的部分和不变的部分，抽化出变化的部分，我们发现糖果机提供的行为一般是不变的，就是投入硬币、转动曲柄给、退回硬币、机器发放糖果；
 * 而糖果机的状态是可以变化的，可以新增出一种状态来，比如我们说的赢家状态。那么我们这个抽出变化的部分，即我们说的状态
 * <p>
 * 状态模式定义：对象行为的变化是由于状态的变化引入，那么即当内部状态发生变化的时候，就会改变对象的行为，而这种改变视乎就改变了整个类。
 * Created by Administrator on 2017/9/22.
 */
public class App {
    public static void main(String[] args) {
        CandyMachine mCandyMachine = new CandyMachine(6);

        mCandyMachine.printstate();

        mCandyMachine.insertCoin();
        mCandyMachine.printstate();

        mCandyMachine.turnCrank();

        mCandyMachine.printstate();

        mCandyMachine.insertCoin();
        mCandyMachine.printstate();

        mCandyMachine.turnCrank();

        mCandyMachine.printstate();
    }
}
