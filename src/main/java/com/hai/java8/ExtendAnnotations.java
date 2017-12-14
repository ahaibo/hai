package com.hai.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 扩展注解的支持
 * Java 8扩展了注解的上下文。现在几乎可以为任何东西添加注解：局部变量、泛型类、父类与接口的实现，就连方法的异常也能添加注解。
 */
public class ExtendAnnotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
            System.out.println("Extend Annotation...");
        }
    }

    public static void main(String[] args) throws Exception {
        final Holder<String> holder = new @NonEmpty Holder<>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();

        holder.method();
    }
}