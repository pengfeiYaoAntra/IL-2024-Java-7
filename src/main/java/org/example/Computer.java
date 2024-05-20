package org.example;

import java.util.concurrent.TimeUnit;
/*
synchronized keyword
case 1:
public static void main(String[] args) {
        Computer computer = new Computer();
        //Computer computer1 = new Computer();
        new Thread(()->{
            computer.google();
        },"thread1").start();
        new Thread(()->{
            computer.X();
        },"thread2").start();
//        new Thread(()->{
//            computer1.X();
//        },"thread2").start();
    }
    public synchronized void google(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with google account");

    }
    public synchronized void X(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with X account");
    }

       assmuing we have multiple synchronized keywords for methods in one object. as long as one thread
       call one synchronized method at certain time, other thread should wait the current thread to finish.
       synchronized keyword locks the current object: "this"


       in case2:

        public static void main(String[] args) {
        Computer computer = new Computer();
        //Computer computer1 = new Computer();
        new Thread(()->{
            computer.google();
        },"thread1").start();
        new Thread(()->{
            computer.facebook();
        },"thread2").start();
//        new Thread(()->{
//            computer1.X();
//        },"thread2").start();
    }
    public synchronized void google(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with google account");

    }
    public synchronized void X(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with X account");
    }
    public void facebook(){


        System.out.println("you logged with facebook account");
    }

    we assuming one thread call synchronized method, another one call normal method.
    face book will printed out before google. which means there is not resources competition


case 3
 public static void main(String[] args) {
        Computer computer = new Computer();
        Computer computer1 = new Computer();
        new Thread(()->{
            computer.google();
        },"thread1").start();
        new Thread(()->{
            computer1.facebook();
        },"thread2").start();
//        new Thread(()->{
//            computer1.X();
//        },"thread2").start();
    }
    public static synchronized void google(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with google account");

    }
    public static synchronized void X(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with X account");
    }
    public void facebook(){


        System.out.println("you logged with facebook account");
    }
    I added static keyword to google and x()
    for static method with synced, two threads call one object's google and X(), or two threads call two different object's google and X
    google will printed out before x
    which means: static + synchronized = your locker will lock the class itself

    for non static: your locker will lock the current object: this
    this is why facebook is printed out before google




 */

public class Computer {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Computer computer1 = new Computer();
        new Thread(()->{
            computer.google();
        },"thread1").start();
        new Thread(()->{
            computer1.facebook();
        },"thread2").start();
//        new Thread(()->{
//            computer1.X();
//        },"thread2").start();
    }
    public static synchronized void google(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with google account");

    }
    public static synchronized void X(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("you logged with X account");
    }
    public void facebook(){


        System.out.println("you logged with facebook account");
    }
}
