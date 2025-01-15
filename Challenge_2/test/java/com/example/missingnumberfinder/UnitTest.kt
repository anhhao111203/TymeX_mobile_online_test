package com.example.missingnumberfinder

import org.junit.Test
import org.junit.Assert.*

class MissingNumberTest {
    // Success when the missing number is 3:
    @Test
    fun missingNumberTestCase_1() {
        val numsArray = intArrayOf(1, 2, 4, 5, 6)
        assertEquals(3, findMissingNumber(numsArray))
    }

    // Success when the missing number is 1:
    @Test
    fun missingNumberTestCase_2() {
        val numsArray = intArrayOf(2, 3, 4, 5)
        assertEquals(1, findMissingNumber(numsArray))
    }

    // Success when the missing number is 6:
    @Test
    fun missingNumberTestCase_3() {
        val numsArray = intArrayOf(1, 2, 3, 4, 5)
        assertEquals(6, findMissingNumber(numsArray))
    }

    // Success when the missing number is 4:
    @Test
    fun missingNumberTestCase_4() {
        val numsArray = intArrayOf(5, 1, 2, 6, 3)
        assertEquals(4, findMissingNumber(numsArray))
    }
}