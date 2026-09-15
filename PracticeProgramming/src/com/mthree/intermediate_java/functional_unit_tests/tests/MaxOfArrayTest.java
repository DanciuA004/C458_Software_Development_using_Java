package com.mthree.intermediate_java.functional_unit_tests.tests;

import org.junit.Test;

import static com.mthree.intermediate_java.functional_unit_tests.unit_testing.arrays.ArrayExerciseA.maxOfArray;
import static org.junit.Assert.assertEquals;

public class MaxOfArrayTest {

    /*  Test PLan:
        maxOfArray( {} ) -> 0
        maxOfArray( {1} ) ->  1
        maxOfArray( {3, 4, 5} ) ->  5
        maxOfArray( {-9000, -700, -50, -3} ) ->  -3
     */

    /*
    ARRANGE - for simple methods, this means setting up the parameters

    ACT - for simple methods, this generally means calling the method under test
    and then capturing its return to assert on

    ASSERT - basically just a conditional that proves the result is what
    you expect it to be, plus an extra message to display if it doesn't match.

    There are a wide variety of assert types, here we
    just want to assert that it returned false. But we could have also used
    assertEquals and passed in a false value.
    */

    @Test
    public void testEmptyArray(){
        // ARRANGE
        int[] parameters = {};

        // ACT
        int result = maxOfArray(parameters);

        // ASSERT
        assertEquals(0, result);
    }

    @Test
    public void testSingleArray(){
        int[] parameters = {1};
        int result = maxOfArray(parameters);
        assertEquals(1, result);
    }

    @Test
    public void testMultiplePositiveArray(){
        int[] parameters = {1,2,3,4,5};
        int result = maxOfArray(parameters);
        assertEquals(5, result);
    }

    @Test
    public void testMultipleNegativeArray(){
        int[] parameters = {-1,-2,-3,-4,-5};
        int result = maxOfArray(parameters);
        assertEquals(-1,result);
    }

}
