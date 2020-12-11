package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;
    ReentrantLock lock = new ReentrantLock();
    Condition foo = lock.newCondition();
    Condition bar = lock.newCondition();
    volatile int flag = 1;
    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            try{

                lock.tryLock();
                if(flag==0){
                    foo.await();
                }
                System.out.print("foo");
                flag = 0;
                bar.signal();
            }
            catch(Exception e){
                System.out.println(e);
            }finally{
                lock.unlock();
            }
        }
    }

    public void bar() throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            try{
                lock.tryLock();
                if(flag==1){
                    bar.await();
                }
                System.out.print("bar");
                flag = 1;
                foo.signal();
            }
            catch(Exception e){
                System.out.println(e);
            }finally{
                lock.unlock();
            }
        }
    }
}

class Aaa{
    public static void main(String[] args) {
        final FooBar fb = new FooBar(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fb.foo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fb.bar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}