package ua.edu.ucu.iterators;

import java.util.Iterator;
import ua.edu.ucu.function.IntUnaryOperator;

public class MapIterator implements Iterator<Integer> {
    Iterator<Integer> olditer;
    IntUnaryOperator mapper;

    public MapIterator(Iterator<Integer> iterator,IntUnaryOperator mapper) {
        this.olditer = iterator;
        this.mapper = mapper;

    }
    @Override
    public boolean hasNext() {
        return olditer.hasNext();
    }

    @Override
    public Integer next() {
        return mapper.apply(olditer.next());

    }
}
