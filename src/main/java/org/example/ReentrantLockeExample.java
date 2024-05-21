package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockeExample {
    // just increment by 1
    //step 1: you need to create reentrant locker object
    //step 2: you need to lock your resources
    //step 3: integer increment by 1
    // step 4: release lock
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int count = 0;
    public void increment(){
        reentrantLock.lock();
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + " count is " + count);
        }finally {
            reentrantLock.unlock();//release your lock
        }
    }

    public static void main(String[] args) {
        ReentrantLockeExample reentrantLockeExample = new ReentrantLockeExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    reentrantLockeExample.increment();
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(task,"thread1");
        Thread thread2 = new Thread(task,"thread 2");
        thread1.start();
        thread2.start();

    }

}
