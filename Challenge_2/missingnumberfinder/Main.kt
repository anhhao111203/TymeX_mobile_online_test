package com.example.missingnumberfinder

// Function to find the missing number in the array:
fun findMissingNumber(numsArray: IntArray): Int {
    val n = numsArray.size + 1

    // Sum of numbers from 1 to n+1:
    val totalSum = (n * (n + 1)) / 2

    // Sum of elements in the array:
    val arraySum = numsArray.sum()

    // The missing number:
    return totalSum - arraySum
}

fun main() {
    val numsArray = intArrayOf(3, 7, 1, 2, 6, 4)
    val missingNumber = findMissingNumber(numsArray)
    println("The missing number is: $missingNumber")
}
