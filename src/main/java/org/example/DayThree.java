package org.example;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * what is functional interface?
 * in java, we have annotation: @FunctionalInterface
 * functional interface is an interface that has only ONE abstract method
 * Common examples:
 *      runnable
 *      callable
 *      comparator
 *      consumer
 *      function<T,R>
 *      supplier
 *      Predicate
 *
 * lambda expression:
 *  it is a function with name
 *  ways to use lambda expression:
 *  1: No Parameter: () -> System.out.print("")
 *  2: One Parameter: (int a) -> return a * a;
 *
 *  before java 8
 *  new Thread(new Runnable() {
 *          @Override
 *          public void run(){
 *
 *              // your business logic
 *          }
 *
 *  }).start()
 *
 *  after java 8
 *  new Thread(()->System.out.print("my thread")).start();
 *
 *  stream api != restful api
 *   deal with stream witout effacting original java collections (list, set, hashmap)
 *  two parts:
 *      intermediate functions
 *          filter()
 *          map()
 *          sorted()
 *          skip()
 *          limit()
 *          distinct()
 *
 *      terminal functions
 *          forEach()
 *          anyMatch()/allMatch()/NoneMatch()
 *          count()
 *          reduce()
 *          min()/max()
 *          collect()
 *
 *
 *  collections vs stream api
 *
 *  in java, collections are data structure: hashmap, list, array, set and .. -> all data that are store
 *  using java collections are stored in memory, where stream does not store data in memory
 *
 *  collections can be modified by adding, removing, whereas stream api operations do not modify the original collection.
 *
 *  collections in java provide methods for accessing and iterating elements, whereas stream api only provides a set of operations
 *
 *  you are dealing with a large file -> parallel stream api
 *  in parallel stream api, stream api will split your works into smaller streams that be processed in parallel
 *
 *
 *
 *  optional:
 *  to avoid null pointer exception
 *
 *
 *
 *
 *
 *
 */
public class DayThree {
    public static void main(String[] args) {
//        Calculator add = (a,b) -> a +b;
//        int res = add.calculate(1,2);
//        System.out.println(res);
//
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6);// double every elements in list
//        //after java 8
//        list.forEach(n ->{
//            int x = n * n;
//        });
//
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b -a);
//
//        List<String> list2 = Arrays.asList("a","c","b","d");
//        Collections.sort(list,(a,b)->b.compareTo(a));

        //case 1 stream api
        // filter out that strings start with "c"
        // c4 c3
        List<String> myList = Arrays.asList("a1","a2","b1","c4","c3");
        myList.stream().filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // case 2: find a string that contains lower case
        List<String> myList1 = Arrays.asList("THIS","is","a", "STREAM","API");
        myList1.stream()
                .filter(s ->s.chars().anyMatch(Character::isLowerCase))
                .forEach(System.out::println);
        //case 3: select strings contains a number
        List<String> myList2 = Arrays.asList("AAAAa1","AABBBB2","BBBBBB1","cads1324","cksjfd");
        myList2.stream()
                .filter(str ->str.matches(".*\\d.*"))
                .forEach(System.out::println);
        //case 4: select strings contians a number and lowercase
        myList2.stream()
                .filter(str ->str.matches(".*\\d.*"))
                .filter(s ->s.chars().anyMatch(Character::isLowerCase))
                .forEach(System.out::println);

        //case 5: find max in a list
        List<Integer> list = Arrays.asList(1,6,99,2,3,100,15,30);
        Integer max = list.stream()
                .reduce(Integer::max).get();
        Integer min = list.stream()
                .reduce(Integer::min).get();
        System.out.println("max is " + max + " min is " + min);

        //case 6: find numbers that are divisible by 3 and 5
        List<Integer> res = list.stream()
                .filter(n->n % 3 == 0 && n % 5 ==0)
                .collect(Collectors.toList());
        System.out.println(res);

        //case 7: find  words in a given string list and return unique words
        List<String> mywords = Arrays.asList("there is a student", "here is a dog","there is a cat", "I love Java");
        List<String> words = mywords.stream()
                .flatMap(s->Arrays.stream(s.split(" ")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(words);
        System.out.println(mywords);
        //case 8: comparing general stream api with parallel stream api
        list.stream()
                .map(numbers ->{
                    String threadname = Thread.currentThread().getName();
                    System.out.println("number is " + numbers + " in " + threadname);// we are using main thread
                    return numbers * numbers;
                })
                .forEach(result -> System.out.println("res is " + result));

        list.parallelStream()
                .map(numbers ->{
                    String threadname = Thread.currentThread().getName();
                    System.out.println("number is " + numbers + " in " + threadname);// we are using fork join pool threads
                    return numbers * numbers;
                })
                .forEach(result -> System.out.println("res is " + result));


        Student student1 = new Student("Antra","Antra",Optional.of("123456789"));
        Student student2 = new Student("pengfei","yao",Optional.empty());

        Optional<String> phoneNumberSt1 = student1.getPhoneNumber();
        if(phoneNumberSt1.isPresent()){
            System.out.println(phoneNumberSt1.get());
        }else{
            System.out.println("phone number is null for this student");
        }
        if(student2.getPhoneNumber().isPresent()){
            System.out.println(student2.getPhoneNumber().get());
        }else {
            System.out.println("student 2 does not have phone number");
        }

     }



}


