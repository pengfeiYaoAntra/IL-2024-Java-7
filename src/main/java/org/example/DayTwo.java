package org.example;

import java.util.*;

public class DayTwo {

    /*
    * java collections -> arraylist, hashmap, heap, hashset tree set..
    *
    *
    *       ArrayList
    *       Implements: random access-> supports fast random access and retrieve any random element in the constant time - > O(1)
    *               arraylist has 1 M data -> 10th element and 999999element ->O(1)
    *         Hint: when you create a new array list without offering a specific size ( ArrayList<Integer> ints = new ArrayList<>();)
    *                   you are actually creating an empty array. this array will be resized from 0 to 10, when you add first
    *                   element to  it.
    *                  what about second time? or When resizing function called again?
    *                  when you add 11th element into arraylist
    *                       what is new capacity?
    *                        new capacity = 1.5 * old capacity
    *
    *         modCount： fail-fast： count how many times your arraylist is changed by thread（s）
    *
    *      time complexity
    *           add() -> O(1): best case
    *                   O(n) worst case -> resizing function should be called
    *
    *           add(index, element): O(n)
    *           get(): always O(1)
    *           remove(): O(n)
    *           indexOf(): O(N)
    *           contains(): O(N)
    *
    *
    *      Stack + vector:
    *           first in last out
    *           thread safe -> synchronized
    *
    *           resizing function: new capacity = 2 * old capacity
    *                               or new capacity = capacityIncrement + old capacity
    *
    *       time complexity stack
    *           push : O(1)
    *           pop() : O(1)
    *           size(): O(1)
    *
    *       time complexity vector
    *           add O(1)
    *           remove O(1)
    *           size O(1)
    *
    *
    *       commons for arraylist ,stack and vector:
    *           they both extends abstract array list and implements random access
    *       different for arraylist , stack and vector:
    *           stack and vector are thread safe, where arraylist is not
    *           grow method is different with arraylist, stack + vector = 2 * old, arraylist = 1.5 * old
    *
    *       LinkedList
    *           no random access
    *           no thread safe
    *           how add last implemented in linkedlist?
    *            void linkLast(E e) {
                    final Node<E> l = last;// step1 : remember the last node in the current linked list
                    final Node<E> newNode = new Node<>(l, e, null);// step2: creating a new node "e" and previous node is l
                    last = newNode;// step3: assigning node 'e' to last node
                    if (l == null)// when linked list is empty
                        first = newNode;
                    else
                        l.next = newNode; // not empty
                    size++;
                    modCount++;
                }
                *
                * time complexity linkedlist
                *   add O(1)
                * add(index, element) O(N)
                * remove() : O(N)
                * get() : O(N)

    *
    *
    *   Deque: FILO(first in last out) and FIFO(first in first out)
    *
    *
    *
    *   priority queue
    *       no thread safe
    *       using array to store data
    *       resizing: new = 1.5 * old
    *
    *   time complexity:
    *       insertion(add(), offer()) : O(logn)
    *       deletion for min and max (poll, remove) : O(logn)
    *       peek at min / max: O(1)
    *          size: O(1)
    *
    *
    *   HashMap
    *       **how does hashing function work in hashmap?
    *            static final int hash(Object key) {
                        int h;
                        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
                    }
                    *  (h = key.hashCode()) = h: 1111 1111 1111 1111 1111 0000  1110 1010
                    *          (h >>> 16) :      0000 0000 0000 0000 1111 1111  1111 1111
                    *            ^:              1111 1111 1111 1111 0000 1111  0001 0101
                    *
                    * **********************************************************************
                    * tab is array of node: Node<K,V>[] tab;
                    * index of key: (p = tab[i = (n - 1) & hash]) == null)
                    * *****where n is len of tab******
                    *       i = (n - 1) & (hash = h):  1111 1111 1111 1111 0000 1111  0001 0101
                    *              (n-1)               0000 0000 0000 0000 0000 0000  0000 1111
                    *       BITWISE AND                0000 0000 0000 0000 0000 0000  0000 0101 =
                    *                                   = 0101 = 0 * 2^3 + 1 * 2^2 + 0 * 2^1 + 1 * 2^0
                    *                                   = 5

    *       bitwise AND: it returns 1 if and only if both bits are 1, else return 0
    *               x  y     x & y
    *               0   0      0
    *               1   0       0
    *               0   1       0
    *               1   1       1
    *
    *      bitwise exclusive OR (^): it returns 0 if both bits are the same, else return 1
    *               x  y     x & y
    *               0   0      0
    *               1   0       1
    *               0   1       1
    *               1   1       0
    *
    *       bitwise inclusive OR(|): it returns 1 as long as bit is 1, else return 0
    *               x  y     x & y
    *               0   0      0
    *               1   0       1
    *               0   1       1
    *               1   1       1
    *
    *
    *
    *       bitwise shift: >>> right || <<< left
    *       bits are divided by 2 for right
    *                   times by 2 for left
    *           0100 = 4 >>> 0010 == 2
    *           0100 = 4 <<< 1000 == 8
    *
    *
    * after java 8
    * [[1],[2],[3]..[8]] -> bucket + linked list / red-black tree
    *   |
    *   1
    *   |
    *   3 ... 8 elements here
    * ***** when hashmap starts to transfer from linked list to red-black tree
    *    len of linked list is greater then TREEIFY_THRESHOLD = 8; hashmap will change linked list to red-black tree
    *
    * ***** when hashmap starts to transfer red-black tree to linked list
    *   the number of elements are less than  static final int UNTREEIFY_THRESHOLD = 6; in red-black tree
    * [[1],[2],[3]..[8]] -> bucket + linked list / red-black tree
    *   |
    *   1
    *   |
    *   3 .. 6 elements here
    *
    *
    * resize(): new size = 2 * old size
    *       if (oldCap > 0) { // step1 hashmap is not empty
                    if (oldCap >= MAXIMUM_CAPACITY) { // you have huge hashmap
                        threshold = Integer.MAX_VALUE;
                        return oldTab;
                    }
                    else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && // general size hashmap
                             oldCap >= DEFAULT_INITIAL_CAPACITY) // new size = old size * 2
                        newThr = oldThr << 1; // double threshold
                }
                else if (oldThr > 0) // initial capacity was placed in threshold, your hashmap is empty
                    newCap = oldThr;
                else {               // zero initial threshold signifies using defaults
                    newCap = DEFAULT_INITIAL_CAPACITY;
                    newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
                }
    *
    *
    *
    * the process of put function
    * step 1: check your hashmap is empty? yes-> resizing your hashmap -> go to step 2. no -> go to step 2
    * step 2: calculating hash value and find the index where we should put
    * step 3: if there is not key exits (location is null), we just do insertion
    * step 4: if there exits hash collision, hashmap will do this:
    *          1: if hashmap is using red- black tree, then call red-black tree insertion
    *           2: if hashmap is  using linked list, then put the value
    *               2.1: after insertion, hashmap will double check the current size of linked list is greater then 8
    *               2.2: if yes, then change to red-black tree;
    * step 5: if there exits duplicate key, then replace it with new value
    * step6:  check the size, if the size is greater than threshold, then resizing
    *
    * time complexity of hashmap:
    *
    *           the best case:   the worse case:  when all elements are in a single bucket;  [[0]....[15]]
    *   insertion O(1)             O(N)
    *   deletion O(1)               O(N)
    *   search   O(1)               O(N)
    *
    *

    * Hashset is similar to hashmap, bc hashset is using hashmap
    * hashset vs hashmap
    * hashmap is k-v pairs, hashset is not and stores unique elements and is internally backed by a hashmap
    * hashmap does not allow duplicate ket but allows duplicate values, where hashset does not allow duplicate elements
    * hashmap allows one null key and duplicate null values, where hashset allows one null element
    *
    *
    *hashset vs tree set
    * hashset is using hash table, where tree set is using red-black tree
    * time complexity of hashset is same with hashmap, where tree set is O(logN) for add, remove, contains
    * ordering: hashset is arbitrary, tree set is sorted
    * null value: hashset is allowed, tree set is not allowed
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
     */



    public static void main(String[] args) {
        System.out.println("");
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);

        Deque<String> deque = new LinkedList<>();
        deque.addFirst("1111");
        deque.addLast("2222");

        String first = deque.removeFirst();
        String last = deque.removeLast();


        Deque<String> deque1 = new ArrayDeque<>();

        deque1.add("1");
        deque1.add("2");

        Iterator<String> stringIterator = deque1.iterator();
        while (stringIterator.hasNext()){
            String element = stringIterator.next();
            System.out.println(element);
        }


        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());



    }
}
