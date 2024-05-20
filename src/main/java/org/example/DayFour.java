package org.example;

/**
 * Day four
 *what is JVM?
 * it is Java virtual machine that allows your computer: mac book or windows to execute java programming
 *
 *         System.out.println("Hello, JVM"); -> how can I print out Hello,JVM
 *         first step: you need java compiler, Java compiler will compile your code to bytecode -> will create .class file
 *
 *         second step. bytecode will be loaded into JVM by class loader
 *         and execution engine will execute bytecode
 *         runtime data area: provide a space to store bytecode, objects, parameter, local variables, return value
 *
 *
 * heap vs stack
 *
 * what is stack?
 *  it is used to store data for your class method-> local variables, method params, return value. it allows thread to
 *  keep tracking your method invocation
 *
 *  what is heap?
 *  heap is used to some information about object -> ALl of new created object are stored in the heap memory
 *      in our heap, we have different segments or regions:
 *              young generation: most of new created objects are stored in young generation -> for all small objects
 *                  eden space: all new created small objects are stored in here
 *                  survivor space: after one GC(garbage collection), all new small objects are moved from eden space to survivor space
 *
 *              old generation: some of new created objects are stored in old generation -> when your objects are really big
 *                   all created small objects are moved from survivor space to old generation after few GC
 *               metaspace: store definition of your class like .class file
 *
 *
 *
 *   how java knows one object is alive or dead?
 *   in old way: reference counting method -> we are not use this anymore
 *
 *
 *  in new way: the gc roots method:
 *      gc roots: you can think it is a tree or linked list: you can reach all alive objects from gc roots
 *      what are informations we store in gc roots
 *          local variables, method params
 *          static variables
 *          actives threads
 *
 *GC algorithm
 * term: stop - the - world:
 *      your application is stopped for your GC to finish some steps
 *
 * normal deletion:
 * deletes some objects not reachable  from heap
 *
 * normal deletion with compacting:
 * deletes some objects not reachable from heap and compacting and storing your heap and store new objects
 * normal deletion is slow: bc stop-the-world:
 *
 * CMS: concurrent mark and sweep:
 *  we want to reduce number of STWs -> will increase the performance of your application
 *  steps:
 *          first step: initial marking: (your application will have STW)
 *              is there any objects can be directly reached from GC roots
 *              [bitmap]
 *          second step:(no STW)
 *                  concurrent marking and sweeping
 *                  in this step, your application is running and the CMS will mark some objects that are not reach from GC roots
 *                  all marking step is happening in parallel
 *                  during this step, there are some objects can become to dead or reachable from gc!
 *
 *           thrid step:
 *              remark step: (we will have STW)
 *                  CMS will redetermine all of objects: is it dead or is reachable object?
 *
 *            last step:
 *            concurrent sweeping step:
 *                  remove some objects that are not marked as alive
 *                  after this, the heap memory is free.
 *
 *
 * G1:
 *  segmenting the heap memory into multiple small regions (2048 regions). Each region is marked as either young(eden or survivor) or old
 *
 *
 *
 *
 *
 *  How many ways to create a thread?
 *
 *  1: we can extends Thread class
 *  2: implements runnable
 *  3: implements callable
 *
 *
 * what are thread states in java?
 *
 *
 *
 * functions in thread: wait, sleep, notify, notifyall()
 *
 * wait(): can be called by a thread to "give up a shared resources" and go to wait state. when the thread should wake up? when other thread call notify() or notifyAll()
 * sleep():can be called by a thread and "does not give up a shared resources" and go to wait state for a specified a mount of time. when the thread should wake up? when other thread call notify() or notifyAll()
 * notify(): to take up another waiting thread that a shared resource is available
 * notifyAll():to take up another all waiting threads that a shared resource is available
 *
 *sleep() vs wait()
 * This is line: 1 12:35:15 	Thread-0 I calling sleep() and will sleep 1s ->
 *          thread 0 calls log first and thread 1 has to wait outside -> sleep function does not release the resources(CPU)
 * This is line: 2 12:35:16 	Thread-0 wake up, I will call wait() for 2s ->
 *          thread 0 is waked up. thread 1 does not call and get the shared resources
 * This is line: 3 12:35:16 	Thread-1 I calling sleep() and will sleep 1s
 *          thread 0 is calling wait() and thread 1 is calling sleep() function -> thread 0 release the shared sources
 * This is line: 4 12:35:17 	Thread-1 wake up, I will call wait() for 2s
 *          thread 1 finished
 *
 * This is line: 5 12:35:18 	Thread-0 I waited for 2s, I want to call sleep() again for 10s
 *          thread 0 sleep again, thread 1 does not have the shared resources and has to wait for 10s
 * This is line: 6 12:35:28 	Thread-0 wake up
 *                  thread 0 is waked up
 * This is line: 7 12:35:28 	Thread-1 I waited for 2s, I want to call sleep() again for 10s
 *          thread 1 has the shared resources, bc thread 0 is waked up
 * This is line: 8 12:35:38 	Thread-1 wake up
 *
 *
 *
 * run() vs start()
 * run method will not create a new thread to execute your code(using main thread), while start will create a new thread to execute
 * if you are using run method, your code will be executed immediately. start() will not start code immediately until there has cpu resources
 * you can call run method as much as you want, whereas start() function only can be called one time -> start will change the state of your thread
 *
 * volatile:
 * a way to communicate between different threads
 *
 * you have JMM: will help your threads to achieve and mange the communication between threads.
 *
 *
 *
 *
 *
 * prevent to reordering
 *     volatile int a= 0;
 *     volatile int b = 1;
 *     volatile int c = 2;
 *     volatile int d = a + b;
 *     volatile int e = c +d;
 *
 * synchronized keyword
 *
 *
 *
 *
 *
 */

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread state for a thread which has not yet started.
 */

//NEW,
//
//        /**
//         * Thread state for a runnable thread.  A thread in the runnable
//         * state is executing in the Java virtual machine but it may
//         * be waiting for other resources from the operating system
//         * such as processor.
//         */
//        RUNNABLE,
//
//        /**
//         * Thread state for a thread blocked waiting for a monitor lock.
//         * A thread in the blocked state is waiting for a monitor lock
//         * to enter a synchronized block/method or
//         * reenter a synchronized block/method after calling
//         * {@link Object#wait() Object.wait}.
//         */
//        BLOCKED,
//
//        /**
//         * Thread state for a waiting thread.
//         * A thread is in the waiting state due to calling one of the
//         * following methods:
//         * <ul>
//         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
//         *   <li>{@link #join() Thread.join} with no timeout</li>
//         *   <li>{@link LockSupport#park() LockSupport.park}</li>
//         * </ul>
//         *
//         * <p>A thread in the waiting state is waiting for another thread to
//         * perform a particular action.
//         *
//         * For example, a thread that has called {@code Object.wait()}
//         * on an object is waiting for another thread to call
//         * {@code Object.notify()} or {@code Object.notifyAll()} on
//         * that object. A thread that has called {@code Thread.join()}
//         * is waiting for a specified thread to terminate.
//         */
//        WAITING,
//
//        /**
//         * Thread state for a waiting thread with a specified waiting time.
//         * A thread is in the timed waiting state due to calling one of
//         * the following methods with a specified positive waiting time:
//         * <ul>
//         *   <li>{@link #sleep Thread.sleep}</li>
//         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
//         *   <li>{@link #join(long) Thread.join} with timeout</li>
//         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
//         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
//         * </ul>
//         */
//        TIMED_WAITING,
//
//        /**
//         * Thread state for a terminated thread.
//         * The thread has completed execution.
//         */
//        TERMINATED;

public class DayFour {
    private AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) {

//        System.out.println("Hello, JVM");
//        byte[] allocation = new byte[300000 * 1024];
//        //-XX:+PrintGC

//        MyThread myThread = new MyThread();
//        myThread.setName("my first thread");
//        myThread.start();


        //do wait() and sleep()
//
//        DayFour dayFour = new DayFour();
//        new Thread(dayFour::test).start();//this is thread 0
//        new Thread(dayFour::test).start();// this thread 1


    }
    private synchronized void test(){
        try{
            log("I calling sleep() and will sleep 1s");
            Thread.sleep(1000);
            log("wake up, I will call wait() for 2s");
            wait(2000);

            log("I waited for 2s, I want to call sleep() again for 10s");
            Thread.sleep(10000);
            log("wake up");
            notify();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void log(String s){
        System.out.println("This is line: " + atomicInteger.incrementAndGet() + " " + new Date().toString().split(" ")[3]
        + " \t" + Thread.currentThread().getName() + " " + s);
    }




}
