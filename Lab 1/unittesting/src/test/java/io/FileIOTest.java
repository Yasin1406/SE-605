package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIOTest {

    private FileIO fileIO = new FileIO();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFile() {
        int[] expected = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
        int[] actual = fileIO.readFile("/home/yasin/SE-605/Lab 1/unittesting/src/test/resources/grades_valid.txt");
        assertArrayEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_file_does_not_exist() {
        fileIO.readFile("/home/yasin/SE-605/Lab 1/unittesting/src/test/resources/grades.txt");
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_file_is_empty() {
        fileIO.readFile("/home/yasin/SE-605/Lab 1/unittesting/src/test/resources/empty_file.txt");
    }

    @Test (expected = NumberFormatException.class)
    public void test_for_invalid_entry() {
        fileIO.readFile("/home/yasin/SE-605/Lab 1/unittesting/src/test/resources/grades_invalid.txt");
    }
}