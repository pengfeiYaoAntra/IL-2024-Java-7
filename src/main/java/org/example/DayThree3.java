package org.example;

import java.util.concurrent.*;

public class DayThree3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //case 1: how can you use runAsync that takes one param
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(

                ()->{
                    System.out.println(Thread.currentThread().getName());
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
        );
        System.out.println(completableFuture.get());
        //case 2: how can you use runAsync that takes two params
        //step 1: create your  thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(
                ()->{
                    System.out.println(Thread.currentThread().getName());
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                },executorService
        );
        System.out.println(completableFuture1.get());
        executorService.shutdown();

        //case 4: how can you use supply async that takes one parame
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            // sending an email -> studnet 1

                System.out.println(Thread.currentThread().getName());
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "Hello, I am supply async";
        });
        System.out.println(completableFuture2.get());

        //case 5: use supply async that takes two parame
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());

            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "Hello, I am supply async 3";

        },executorService1);
        System.out.println(completableFuture3.get());
        executorService1.shutdown();


        // case 6: how can you throws exception and when the task is complete and print a result
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(()->{
            //main block
            System.out.println(Thread.currentThread().getName());

            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "Hello,I am completable future 4";

        },executorService2).whenComplete((r,e)->{
            if(e == null){
                System.out.println("when complete is finished, then return something: " + r);
            }
        }).exceptionally( e->{
            e.printStackTrace();
            System.out.println("exception is finished");
            return null;
        });
        System.out.println(completableFuture4.get());
        // throws exception
        CompletableFuture<String> completableFuture5 = CompletableFuture.supplyAsync(()->{
            //main block
            System.out.println(Thread.currentThread().getName());
            int a = 0;
            int i = 10;

            if(i > 0){
                a = i / 0;
            }
            // math

            return "Hello, the value of a is " ;
            //pool-3-thread-2
            //exception is finished
            //null

        },executorService2).whenComplete((r,e)->{
            if(e == null){
                System.out.println("when complete is finished, then return something: " + r);
            }
        }).exceptionally( e->{
            e.printStackTrace();
            System.out.println("exception is finished");
            return null;
        });
        System.out.println(completableFuture5.get());
        executorService2.shutdown();


        //case 6:
        ExecutorService executorService3 = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture6 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("This is step 1");
            return 1;// 1
        },executorService3)
                .thenApply(r->{
                    System.out.println("this is step 2");
                    return r + 1;// 2
                }).thenApply(r->{
                    System.out.println("This is step 3");
                    return r + 2;//4
                }).whenComplete((r,e) ->{
                    if(e == null){
                        System.out.println("whenComplete: the final result is " + r);
                    }
                }).exceptionally(e ->{
                    e.printStackTrace();
                    return null;
                });
        System.out.println(completableFuture6.get());
        //whenComplete: the final result is 4
        //4


        executorService3.shutdown();

        ExecutorService executorService4 = Executors.newFixedThreadPool(3);
        System.out.println(CompletableFuture.supplyAsync(()->"this is my result A").thenRun(()->{
            System.out.println("then run");
        }).join());
        System.out.println(CompletableFuture.supplyAsync(()->"this is my result B").thenAccept(r->{
            System.out.println(r);
        }).join());
    }
}



