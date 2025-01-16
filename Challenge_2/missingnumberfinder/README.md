# Missing Number Finder

This Kotlin program determines the missing number in a sequence of integers. It's used to calculate the missing number from an array of integers ranging from 1 to n, where one number is missing.

---

## Overview

The **Missing Number Finder** program computes the missing number in a given integer array. For example, if the array contains numbers from 1 to n with one number missing, the program will identify and output the missing number.

---
## Example

Here is an example of how the program works:

```kotlin
fun main() {
    val numsArray = intArrayOf(3, 7, 1, 2, 6, 4)
    val missingNumber = findMissingNumber(numsArray)
    println("The missing number is: $missingNumber")  // Output: 5
}

