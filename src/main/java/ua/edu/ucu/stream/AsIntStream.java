package ua.edu.ucu.stream;


import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Iterator;


import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iter;

    public AsIntStream(Iterator<Integer> iter) {
        this.iter = iter;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIterator(values));
    }

    @Override
    public Double average() {
        if (!iter.hasNext()) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        int counter = 0;
        while (iter.hasNext()) {
            counter++;
            sum += iter.next();
        }
        return (double) (sum / counter);

    }

    @Override
    public Integer max() {
        if (!iter.hasNext()) {
            throw new IllegalArgumentException();
        }
        int maxValue = Integer.MIN_VALUE;
        for (Iterator<Integer> it = iter; it.hasNext(); ) {
            int el = it.next();
            if (el > maxValue) {
                maxValue = el;
            }
        }
        return maxValue;
    }

    @Override
    public Integer min() {
        if (!iter.hasNext()) {
            throw new IllegalArgumentException();
        }
        int minValue = Integer.MAX_VALUE;
        for (Iterator<Integer> it = iter; it.hasNext(); ) {
            int el = it.next();
            if (el < minValue) {
                minValue = el;
            }
        }

        return minValue;
    }

    @Override
    public long count() {
        long counter = 0;
        while(iter.hasNext()) {
            counter++;
            iter.next();
        }
        return counter;
    }

    @Override
    public Integer sum() {
        Integer sum = 0;
        while(iter.hasNext()) {
            sum += iter.next();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(iter, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while(iter.hasNext()){
            Integer el = iter.next();
            action.accept(el);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(iter, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(iter, func));

    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int i = identity;
        while(iter.hasNext()){
            i = op.apply(i, iter.next());
        }
        return i;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arr = new ArrayList<>();

        while(iter.hasNext()){
            arr.add(iter.next());
        }

        int[] array2 = new int[arr.size()];
        int index = 0;
        for(int el: arr){
            array2[index] = el;
            index++;

        }
        return array2;
    }

}