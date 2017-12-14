package com.hai.test.suggest151;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/1.
 */
public class Sug114_ExceptionConstructor {

    @Test
    public void test1() {
        Person person = new Person(17);
        person.seeMovie();
    }

    class Person {
        public Person(int age) {
            if (age < 18) {
                throw new RuntimeException();
            }
        }

        public void seeMovie() {
            System.out.println("see restrict movie...");
        }
    }


    class Base {
        public Base() throws IOException {
        }
    }


    class Sub extends Base {
        public Sub() throws IOException {
        }
    }

}
