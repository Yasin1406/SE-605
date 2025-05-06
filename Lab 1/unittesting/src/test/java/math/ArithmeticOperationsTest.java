package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    private final ArithmeticOperations arithmeticOperations = new ArithmeticOperations();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divide() {
        double expected = 3.0;
        double actual = arithmeticOperations.divide(18, 6);
        assertEquals(expected, actual, 1e-3);
    }

    @Test (expected = ArithmeticException.class)
    public void test_with_zero_in_denominator(){
        arithmeticOperations.divide(35, 0);
    }

    @Test
    public void multiply() {
        double expected = 30;
        double actual = arithmeticOperations.multiply(5, 6);
        assertEquals(expected, actual, 1e-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_first_number_is_negative(){
        arithmeticOperations.multiply(-5, 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_second_number_is_negative(){
        arithmeticOperations.multiply(5, -6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_both_numbers_are_negative(){
        arithmeticOperations.multiply(-5, -6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_first_number_is_big_for_the_product_to_be_out_of_the_bounds(){
        arithmeticOperations.multiply(Integer.MAX_VALUE, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_second_number_is_big_for_the_product_to_be_in_the_bounds(){
        arithmeticOperations.multiply(3, Integer.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_both_numbers_are_big_for_the_product_to_be_out_of_the_bounds(){
        arithmeticOperations.multiply(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}