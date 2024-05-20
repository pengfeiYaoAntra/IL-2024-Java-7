package org.example;

import java.util.concurrent.*;

public class DayThree2 {

    public static void main(String[] args) {
        // assuming you have three tasks to finish in you project
        // task1 , task 2 and task 3
        // each task takes 500 ms
        // case 1 if you are using one thread
        // the total time is 1500 ms

        long startTime = System.currentTimeMillis();
        // your tasks
        //task 1
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //task 2
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //task 3
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("the cost time is " + (endTime - startTime)); // >1500 ms
        // how can you speedup your project?
        // we can use completable future
        //first step: you need to create thread pool -> assuming we are using fixed thread pool: the number of threads
        // in thread pool are fixed -> three threads

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime1 = System.currentTimeMillis();
        //step 2: create your tasks
        //your task 1
        FutureTask<String> task1 = new FutureTask<>(() ->{
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "task 1";
        });
        // you can submit your task 1 to thread pool
        threadPool.submit(task1);
        //task 2
        FutureTask<String> task2 = new FutureTask<>(() ->{
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "task 2";
        });
        // you can submit your task 2 to thread pool
        threadPool.submit(task2);
        //task 3
        FutureTask<String> task3 = new FutureTask<>(() ->{
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "task 2";
        });
        // you can submit your task 3 to thread pool

        // start and get result from your tasks
        threadPool.submit(task3);
        try{
            task1.get();
            task2.get();
            task3.get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        long endTime1 = System.currentTimeMillis();

        System.out.println("the cost time is : " + (endTime1 - startTime1)); // way less than 1500 ms
        threadPool.shutdown();


    }
}
