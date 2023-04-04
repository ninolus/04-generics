package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

    private final Logger logger = LogManager.getLogger();
    private SimpleList<Integer> testList;

    @BeforeEach
    void setup() {
        testList = new SimpleListImpl<>();

        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
    }

    @Test
    void testAddElements() {
        logger.info("Testing if adding and iterating elements is implemented correctly");
        int counter = 0;
        for (Integer o : testList) {
            counter++;
        }
        assertEquals(5, counter);
    }

    @Test
    void testSize() {
        logger.info("Testing if size() method is implemented correctly");
        assertEquals(5, testList.size());
    }

    @Test
    void testFilterAnonymousClass() {
        logger.info("Testing the filter possibilities by filtering for all elements greater 2");
        SimpleList<Integer> result = testList.filter(new SimpleFilter<>() {
            @Override
            public boolean include(Integer item) {
                int current = item;
                return current > 2;
            }
        });

        for (Integer o : result) {
            assertTrue(o > 2);
        }
    }

    @Test
    void testFilterLambda() {
        logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
        SimpleList<Integer> result = testList.filter(o -> o % 2 == 0);
        for (int o : result) {
            assertTrue(o % 2 == 0);
        }
    }

    @Test
    void testMap() {
        logger.info("Testing mapping objects from int to String");
        SimpleList<String> expected = new SimpleListImpl<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");

        SimpleList<String> result = testList.map(Object::toString);

        Iterator<String> resIter = result.iterator();
        expected.forEach(x -> assertEquals(x, resIter.next()));

    }
}