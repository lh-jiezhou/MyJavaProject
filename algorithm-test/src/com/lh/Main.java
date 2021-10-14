package com.lh;
import java.util.*;

public class Main implements Iterator<Integer> {

    // 队列
    Queue<Integer> queue;

    public Main(Iterator<Integer> iterator) {
        // initialize any member here.
        queue = new LinkedList();
        while (iterator.hasNext()){
            queue.offer(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return queue.peek();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }


}