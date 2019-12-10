package com.asiainfo.crm.blueprint;

/**
 * @author: dong.chao
 * @create: 2019-10-05 15:33
 * @description: 测试
 **/
public class Foo {

    public Foo() {

    }

    public  void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


    public static void main(String[] args) {



    }

}
