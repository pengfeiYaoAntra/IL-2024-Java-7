package org.example;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 * what is CAS?
 * compare and swap -> has atomic operation
 * with cas, three values:
 *      V: the value you would like to change
 *      E: the expected value
 *      N: the new value you would like to set
 *
 *      int i = 2;
 *      Thread A -> trying to increase i by 1, thread A reads i = 2 and set v = 2 and also set E = 2
 *      then thread A increase i = 3. after increasing, thread A will compare V and E: check V == E?
 *              if yes : set N = 3
 *              if no : drop this operation: you may have multiple threads in your system, and they change value of i before thread A set it
 *
 *
 * there is a problem that is called: ABA problem
 * -------------------------time line---------------------------->
 * thread 1 get the value v= A at time 1---------------thread 1 is way slower than thread 2----------------------------------------------------------------thread 1 is able to update the value V at time 3>
 * thread 2 get the value v= A at time 1 ----> thread 2 the time of execution is faster than thread 1---> change value v = B and change back to A at time 2->
 *
 *how to solve this?
 * AtomicStampedReference
 *
 *
 *
 * what is atomic operation?
 * it is a set of operations always execute or run together.
 * if any error or exception, none of them will execute or run.
 *
 *
 * concurrent hashMap
 * [[segments/buckets]...[]] = size 16
 * before java 8
 * array + linkedlist
 * after java 8
 * array + linkedlist/tree
 *
 * can concurrent hashmap has null value and null key?
 * no, it cannot have null value and null key
 *
 * where is lock added to hashmap?
 *  add lock(sync) at each segments(bucket)
 *    |
 * [[0],[1],[]...[15]]  size of array = 16
 *
 * do all functions in concurrent hashmap have lock?
 * no, like get() function
 *
 *
 *
 * blocking queue - can be used by producer and consumer model
 *  producer 1 -> keep adding information  ->                                                           consumer 1
 *   producer 2                keep adding information         [queue stores some informations]         consumer 2
 *
 * in blocking queue, we have two pointers
 *          1: put
 *          2: take
 *
 *
 *
 * pessimistic locker and optimistic locker
 * pessimistic locker: synchronized -> reentrant lock-> a lock is obtained on a shared resources, once it get locked, no other threads allow to access(read, or update or delete)
 *
 * optimistic locker -> writeandRead locker -> a locker is obtained on a shared resources, allows other threads to read the shared resources
 *
 *
 * reentrant lock- pessmistic locker
 * 1: mutual exclusion: only one thread can hold the lock at a time, preventing multiple threads from accessing the same code or data concurrently
 * 2: fairness: all waiting thread can be picked in ordering
 * thread B C and D waiting for accessing
 * 3: reentrant: A thread can get lock again and again
 *
 * unfairness:
 * thread A -> accessing some shared resources <- thread B, C, D are wait for thread A to finish
 * once thread A is finish ->  thread B is picked to access shared resources -> thread B is finish -> thread C is picked ->
 * thread A comes in try to access shared resources -> after thread C is finished and thread A is picked -> ..... thread D is not picked for entire time
 *
 *
 * executor vs executorService vs executors
 *executor is functional interface and has one method takes one parameter -> runnable
 *
 * executorservice -> interface that has methods for submitting tasks and shutting down the service
 *
 * executors-> for creating thread pool -> fixed thread
 *
 *
 *
 *
 *
 * thread pool
 * fixed thread -> initial number of threads you want to have
 * when you know how much number of threads you need in your system.
 *
 * cached thread pool
 * when you do not know how much number of threads you need in your system
 * cached thread pool is an unbounded thread pool.
 * can a thread in cached thread pool be deleted?
 * yes. when a thread is idle for some time.
 * can have negative impact on your system, when you are creating a lot threads
 *
 *
 *  single thread pool
 *  has only one thread.
 *  all submitted task should be executed sequentially.
 *
 *  fork join pool -> implements work stealing pool
 *  when you submit your task, for join pool will divide your task into smaller tasks
 *  these smaller tasks can be executed in parallel -> speed up your application
 *
 *  what is work stealing pool
 *
 *  when a thread thinks it will have idle time, this thread will "steal" tasks from other busy thread
 *  max cpu
 *
 *
 *
 *
 *
 */

public class Day6 {
    private static AtomicInteger atomicInteger2 = new AtomicInteger(0);

    public static void main(String[] args) {


        AtomicInteger atomicInteger = new AtomicInteger(0);
        int expectedValue = 0;
        int newValue =1;
        System.out.println("the result is "+ atomicInteger.compareAndSet(expectedValue,newValue) + " get new value " + atomicInteger.get());
        // case: thread B excepted value is 3, however atomic integer is changed from 3 to 0 by thread A
        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        int expectedValue1 = 3;
        int newValue1 =1;
        System.out.println("the result is "+ atomicInteger.compareAndSet(expectedValue,newValue) + " get new value " + atomicInteger1.get());

        Thread threadA = new Thread(new increment());
        Thread threadB = new Thread(new increment());
        threadA.start();
        threadB.start();

    }
    static class increment implements Runnable{

        @Override
        public void run() {
            for(int i = 0; i < 100;i++){
                int oldValue, newValue;
                do{
                    oldValue = atomicInteger2.get();
                    newValue = oldValue + 1;
                }while (!atomicInteger2.compareAndSet(oldValue,newValue));
            }
            System.out.println(Thread.currentThread().getName() + " final value is" + atomicInteger2.get());
        }
    }

}
