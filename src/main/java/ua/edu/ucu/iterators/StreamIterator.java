package ua.edu.ucu.iterators;

import java.util.Iterator;
import java.util.ArrayList;

public class StreamIterator implements Iterator<Integer> {
    private ArrayList<Integer> elList = new ArrayList<>();
    private int index = 0;

    public StreamIterator(int[] elements) {
        for (Integer el : elements) {
            elList.add(el);
        }

    }

    public StreamIterator() {
        this.elList = new ArrayList<>();
    }



    public boolean hasNext() {
        return (index < elList.size());

    }

    public Integer next() {
//        index++;
        return elList.get(index++);

    }
}




