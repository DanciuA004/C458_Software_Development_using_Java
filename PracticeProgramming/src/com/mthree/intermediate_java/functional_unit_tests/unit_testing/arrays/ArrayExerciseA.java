/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.mthree.intermediate_java.functional_unit_tests.unit_testing.arrays;

/**
 *
 * @author ahill
 */
public class ArrayExerciseA {
    
    /**
     * Given an array of ints, find and return the maximum value.
     * 
     * Example Results:
     * maxOfArray( {1}  ) ->  1
     * maxOfArray( {3,4,5}  ) ->  5
     * maxOfArray( {-9000, -700, -50, -3}  ) ->  -3
     * 
     * @param numbers array of integers
     * @return int max
     */
    public static int maxOfArray(int[] numbers){

        if (numbers.length == 0) {
            return 0;
        }

        int max = numbers[0];

        for (int i : numbers) {

            if (i > max) {
                max = i;
            }

        }

        return max;
    }
    
}
