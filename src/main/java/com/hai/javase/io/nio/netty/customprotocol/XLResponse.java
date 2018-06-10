package com.hai.javase.io.nio.netty.customprotocol;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class XLResponse extends XLObject {
    private int result;// 结果码

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}