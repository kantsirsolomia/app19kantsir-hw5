package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    IntToIntStreamFunction func;
    Iterator<Integer> oldIter;
    private StreamIterator streamIter;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction func){
        this.oldIter = iterator;
        this.func = func;
        this.streamIter = new StreamIterator();
    }
    @Override
    public boolean hasNext() {

        if(!streamIter.hasNext()){
            if (oldIter.hasNext()){
                streamIter=  new StreamIterator(func.applyAsIntStream(oldIter.next()).toArray()) ;
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return true;

        }

    }

    @Override
    public Integer next() {
        return streamIter.next();
    }



}
