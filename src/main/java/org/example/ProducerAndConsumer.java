package org.example;

import java.security.PrivateKey;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumer {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer  = new ProducerAndConsumer();
        //two threads
        Thread producerThread = new Thread(()->{
            try{
                producerAndConsumer.produce();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        Thread consumerThread = new Thread(()->{
            try {
                producerAndConsumer.consume();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        producerThread.start();
        consumerThread.start();
    }
    //producer function here
    public void produce() throws InterruptedException {
        int value = 0;
        while(true){

            queue.put(value++);
            System.out.println("producer: " + value);
        }
    }

    public void consume() throws InterruptedException{
        while(true){
            if(queue.size() == 10){
                int value = queue.take();
                System.out.println("consumer: " + value);
                Thread.sleep(1000);
            }
        }
    }


}
