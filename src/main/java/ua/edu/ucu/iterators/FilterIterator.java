package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer>  {

    Iterator<Integer> oldIter;
    IntPredicate predicate;
    private Integer value;

    public FilterIterator(Iterator<Integer> iterator, IntPredicate predicate) {
        this.oldIter = iterator;
        this.predicate = predicate;

    }
    @Override
    public boolean hasNext() {
        while (oldIter.hasNext()) {
            value = oldIter.next();
            if (predicate.test(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return value;
    }
}
