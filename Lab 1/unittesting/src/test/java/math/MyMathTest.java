package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    MyMath myMath = new MyMath();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void factorial() {
        int expected = 720;
        int actual = myMath.factorial(6);
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_number_is_negative() {
        myMath.factorial(-2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_number_is_greater_than_12() {
        myMath.factorial(13);
    }

    @Test
    public void isPrime() {
        assertTrue(myMath.isPrime(17));
    }

    @Test
    public void is_not_prime() {
        assertFalse( myMath.isPrime(12));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_input_is_less_than_two(){
        myMath.isPrime(1);
    }
}