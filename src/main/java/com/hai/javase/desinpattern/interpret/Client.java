package com.hai.javase.desinpattern.interpret;

/**
 * Created by Administrator on 2018/2/8.
 */
public class Client {
    public static void main(String[] args) {
        String statement = "3 * 2 * 4 / 6 % 5";
        Calculator calculator = new Calculator();
        int result = calculator
                .build(statement)
                .compute();
        System.out.println(statement + " = " + result);
    }
}
/*
解释器模式主要包含如下几个角色：

AbstractExpression: 抽象表达式。声明一个抽象的解释操作，该接口为抽象语法树中所有的节点共享。
TerminalExpression: 终结符表达式。实现与文法中的终结符相关的解释操作。实现抽象表达式中所要求的方法。文法中每一个终结符都有一个具体的终结表达式与之相对应。
NonterminalExpression: 非终结符表达式。为文法中的非终结符相关的解释操作。
Context: 环境类。包含解释器之外的一些全局信息。
Client: 客户类。

抽象语法树描述了如何构成一个复杂的句子，通过对抽象语法树的分析，可以识别出语言中的终结符和非终结符类。
在解释器模式中由于每一种终结符表达式、非终结符表达式都会有一个具体的实例与之相对应，所以系统的扩展性比较好。
 */