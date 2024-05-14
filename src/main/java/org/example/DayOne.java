package org.example;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.util.ArrayList;

public class DayOne {
            /*
            * Day 1 Java
            * AWS -> start right now
            * what is oop?
            *   what is object in java?
            *       State: bank account -> your balance is state
            *       Behaviors: bank account -> deposit and withdrawal
            *
            *   polymorphism:
            *       many forms? -> the same method name can be performed in a different way.
            *       ***Overloading vs overriding
            *           1: overloading: the same method name, but different variables
            *           2: overriding: it happens in a child class, child class would like to inheritance some functions from
            *               parent class -> same method name and variables
            *
            *        Questions:
            *           1: can data members can be overridden?
            *               No,
            *
            *
            *   Encapsulation:
            *       protects and manages your class information. -> make variables / members as private.
            *
            *   ***abstract and interface
            *       abstract:
            *           1: this class cannot be instantiated
            *               1.1:  myAbstractionClass myAbstractionClass = new myAbstractionClass() ; exception here
            *           2: can have both abstract method and non-abstract method
            *            3: allow to have constructor
            *           4:oen child class can only "extends" one abstract class
            *        interface:
            *           1: data member is public static and final
            *           2: you cannot have constructor
            *           3: child class can "implements" more than one interface
            *           4: you can have private method after java 9, default  and static method after java 8
            *           5: method like this:  void thisIsMethod(); you are not required to have method body
            *
            *
            *   **why does java not support mutilple inheritance?
            *       diamond problem
            *           suppose you have a class A  -> method doSomething();
            *           now, you create two child classes: Class B and Class C -> extends from class A
            *           now, you create one child class: Class D ->extends class B and class C -> java does not know which one you want inheritance
            *
            * Inheritance:
            *       some functions can be inheritance from parent class
            *       -> provides code reusability.
            *
            *
            *
            * types in java
            *       primitive type
            *           byte,short, int, long, float, double, boolean
            *            ==
            *
            *       wrapper type
            *           DO NOT use "=="
            *           Integer int1    -> int1.equals(int2)
            *           wrapper type range [-128,127]
            *           Integer int1 = 100;
                         Integer int2 = 100;
                            System.out.println(int1 == int2); -> true the reason is that int1 and int2 are same object,

                          Integer int3 = 333;
                            Integer int4 = 333;
                            System.out.println(int3 == int4);// false -> int3 and int 4 are not same object -> address is not same
            *
            *   **what are different between "==" and equals()?
            *           ==: for primitive type -> comparing the value of primitive type
            *               for wrapper type -> comparing the value of reference -> address
            *           equals(): just comparing valur of wrapper type
            *
            *   String vs String Builder vs String Buffer
            *
            *   String: it is a immutable class -> cannot be change
            *       private final char value[]; you can see final keyword is used in String class
            *   String Builder: you are able to change the value of string builder
            *      there is not final keyword used in string builder class
            *           synchronized keyword is not used
            *
            *   String Buffer  : you are able to change the value of string Buffer
            *        there is not final keyword used in string buffer class
            *           synchronized keyword is used
            *
            *    String firstName = "pengfei";
                String lastName = "Yao";
                String fullName = firstName + " " + lastName; - > string builder is used
                *
                *
                * "this" vs "super"
                *   this:
                *       "this" can be used to reference current object
                *       this keyword is used to call constructor
                *       "this" can be used as a parameter
                *           "this" can be return
                *
                * super:
                *       points to parent object
                *       call parent function
                *       call parent constructor
                *
                *
                * ** Does java use pass-by-value or pass-by-reference?
                *   java uses pass-by-value;
                *   method(List<Integer> myList){ // value of reference -> address
                *
                *   }
                *   for primitive type: pass value of primitive type
                *
                *
                * shallow copy and deep copy
                * shallow copy: both original and copied object point to the same object( address is same)
                *          student1     ---------------------->       heap memory
                *               |
                *           copy function
                *                                                           student(); add:0x123456
                *                   |
                *           student1Copied       __----------->
                *
                *
                * deep copy: original and copied object are pointing to two different address
                *
                *   student1     ---------------------->       heap memory  student(); add:0x123456
                *               |
                *           copy function
                *
                *                   |
                *           student1Copied       __----------->  student(); add:0x0987654
                *
                * static vs non static
                *       Static: can be used with variable, method -> belongs to class -> no "new" keyword need
                *        nonstatic: belongs to an object - > new keyword should be used
                *
                *
                *
                * exceptions in java
                *
                *       throws for checked exception only
                *       try - catch block for checked and unchecked
                *
                *
                * Generic in java: you can pass any type of value
                * class generic, interface, method
                *   T - type
                *   E - element
                * K  - key
                * N - number
                * V - value
                *
             */
            public static void main(String[] args) {

//                Heart heart = new RedHeart();// upcasting
//                heart.run();
            String name = "Antra";



                Integer int1 = 100;
                Integer int2 = 100;// int1 and int2 point to same address
                System.out.println(int1 == int2);// true

                Integer int3 = 333;
                Integer int4 = 333;
                System.out.println(int3 == int4);// false
                Integer int5 = new Integer(100);
                System.out.println(int1 == int5); // false ->int1 and int5 are different object
                System.out.println(int1.equals(int5));//true -> compare value of int1 and int 5


            }
}
class Heart{ // run time poly
    void run(){
        System.out.println(" I am heart class");
    }
}
class RedHeart extends  Heart{
    void run(){
        System.out.println(" I am a red heart class");
    }
}
// compile time poly

class Math{
    int add(int a, int b) {return a + b;}
    int add(int a, int b, int c){
        return a + b+ c;
    }
    double add(double a, double b){
        return a + b;
    }
}
abstract class myAbstractionClass{
    String name = "Antra";
    // allow constructor
    public myAbstractionClass(String name){
        this.name = name;
    }
    //allow general method
    void myMethod(String lastName){
        System.out.println(lastName);
    }
    //abstract method -> if you are using abstract method, there is not method body.
    abstract void testMethod();
}
interface myInterfaceClass{
    String name = "";// public  + static + final
    void thisIsMethod(); // not body is required in interface
    // default method after java 8
    default void myDefaultMethod() {
        System.out.println(" my default method");
    }
    // private method - > yes after java 9
    //    private viod mymethod(){
    //
    //    }
    //

    static int add(int a, int b) {// after java 8
        return a + b;
    }


    /*
    *
    * main(){
    *   Student student = new Student("Antra", 18);
    *   student.out() -> null ,0
    * }
    * demo for: "this" can be used to reference current object
     */
    class Student{
        private  String name;
        private int age;
        public Student(String name, int age){
            this.name = name; // missing this keyword
            this.age = age;
        }
        public void out(){
            System.out.println(name + " " + age);
        }
    }
    //this keyword is used to call constructor
    class ThisTest{
//        ThisTest(){
//            System.out.println("this test no variable constructor");
//        }
//        ThisTest(int count){
//            this();
//            System.out.println("this test  variable constructor" + count);
//        }

        ThisTest(){
            this(50);
            System.out.println("this test no variable constructor");

        }
        ThisTest(int count){

            System.out.println("this test  variable constructor" + count);
        }
    }
    //
    class ThisAsParam{
        void methodParam(ThisAsParam param){
            System.out.println(param);
        }
        void  method2(){
            methodParam(this);
        }

    }
    // People p = new People()
    //p.setAge(10)
    //p.setFirstName("antra")....
    // chaining function
    // p.setAge(10).setFirstName("antra")
    class People implements Cloneable{

        private int age;
        private  String lastname;
        private String firstname;

        private Address address;


        // setter functions that return People class
        public People setAge(int age){
            this.age = age;
            return this;
        }
        public People setFirstName(String first){
            this.firstname = first;
            return this;
        }
        //shallow copy
//        @Override
//        protected Object clone() throws CloneNotSupportedException{
//            return super.clone();
//        }
               @Override
        protected Object clone() throws CloneNotSupportedException{
            People cloned = (People) super.clone();
            cloned.address = new Address(this.address.city);
            return cloned;
        }
    }
    // accessing super class (parent class) method
    class Animal{

        String name;
        Animal(String name){
            this.name = name;
        }
        Animal(){

        }
        void eat(){
            System.out.println("this is parent eat function");
        }
    }

    class Dog extends Animal{
        int age;
        Dog(){
            super("Mika");// super can be used as calling parent constructor
            System.out.println("this is child constructor");
        }
        Dog(int age, String superName){
            super.name = superName;// super can be used as calling parent members
            this.age = age;
        }
        @Override
        void eat(){
            super.eat();
            System.out.println("this is child eat function");
        }
    }
    // printer - pdf, world, excel

    class Printer<T>{
        private T name;
        Printer(T name){
            this.name = name;
        }
        private  static <T> void out(T thing){
            System.out.println(thing);
        }
        private  static <K,V> void out(K key, V value){
            System.out.println(key + " " + value);
        }
        void print(){
            System.out.println(name);
        }
    }

}