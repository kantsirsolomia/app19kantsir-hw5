package ua.edu.ucu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AsIntStreamTest1 {
    private IntStream intStream;
    private IntStream negStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] negValues = {-1, -5, -10, -2, -3};
        int[] intArr = {-1, 0, 1, 2, 3};
        int[] empty = {};
        intStream = AsIntStream.of(intArr);
        negStream = AsIntStream.of(negValues);
        emptyStream = AsIntStream.of(empty);
    }
//
    @Test
    public void testOf() throws Exception {
        assertSame(AsIntStream.class, intStream.getClass());
    }
//
    @Test
    public void testAverage() {
        double result = intStream.average();
        double expexted = 1.0;
        assertEquals(result, expexted, 0.0001);

        double result1 = negStream.average();
        double expexted1 = -4.0;
        assertEquals(result, expexted, 0.0001);


}

//
    @Test
    public void testMax() {
        Integer result = intStream.max();
        assertEquals(Integer.valueOf(3), result);

        Integer result1 = negStream.max();
        assertEquals(Integer.valueOf(-1), result1);
    }
//
    @Test
    public void testMin(){

        Integer result = intStream.min();
        assertEquals(Integer.valueOf(-1), result);

        Integer result1 = negStream.min();
        assertEquals(Integer.valueOf(-10), result1);
    }


    @Test
    public void testCount() throws Exception {
        long result = negStream.count();
        assertEquals(5L, result);

        long result1 = intStream.count();
        assertEquals(5L, result1);

    }
//
    @Test
    public void testSum() throws Exception {
        Integer result = negStream.sum();
        assertEquals(Integer.valueOf(-21), result);

        Integer result1 = intStream.sum();
        assertEquals(Integer.valueOf(5), result1);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        Double res = emptyStream.average();

    }

    @Test
    public void testFilter(){
        IntStream result1 =  intStream.filter(x -> x > 0);
        assertArrayEquals(new int[]{1, 2,3}, result1.toArray());

        IntStream result =  negStream.filter(x -> x > 0);
        assertArrayEquals(new int[]{}, result.toArray());

    }


    @Test
    public void testForEach() throws Exception {
        ArrayList<Integer> check = new ArrayList<>();
        intStream.forEach(check::add);
        assertArrayEquals(new Object[]{-1, 0, 1, 2, 3}, check.toArray());

    }
//
    @Test
    public void testMap() throws Exception {
        IntStream result = negStream.map(x -> x *x);

        assertArrayEquals(new int[]{1, 25, 100, 4, 9}, result.toArray());

        IntStream result1 = intStream.map(x -> x *2);
        assertArrayEquals(new int[]{-2, 0, 2, 4, 6}, result1.toArray());


    }
//
    @Test
    public void testFlatMap() {
        IntStream result = negStream.flatMap(x -> AsIntStream.of(x - 1, x*2, x + 1));
       assertArrayEquals(new int[]{-2, -2, 0, -6, -10, -4, -11, -20, -9, -3, -4, -1, -4, -6, -2}, result.toArray());

        IntStream result1 = intStream.flatMap(x -> AsIntStream.of(x - 1, x << 1, x + 100));
        assertArrayEquals(new int[]{-2, -2, 99, -1, 0, 100, 0, 2, 101, 1, 4, 102, 2, 6, 103}, result1.toArray());


    }

    @Test
    public void testReduce() throws Exception {
        int result = negStream.reduce(0, (sum, x) -> sum += x);
        assertEquals(-21, result);

        int result1 = intStream.reduce(0, (substract, x) -> substract -= x);
        assertEquals(-5, result1);


    }

    @Test
    public void testToArray() throws Exception {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = intStream.toArray();

        assertArrayEquals(expResult, result);
    }



}
