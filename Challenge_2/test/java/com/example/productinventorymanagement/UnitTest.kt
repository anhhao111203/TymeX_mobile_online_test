package com.example.productinventorymanagement

import org.junit.jupiter.api.Assertions
import org.junit.Test

class InventoryManagerTest {
    // List of products in the inventory:
    private val products = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3)
    )

    // Create an instance of InventoryManager:
    private val inventoryManager = InventoryManager(products)

    // Test cases for InventoryManager:
    @Test
    fun calculateTotalInventoryValueTest() {
        val totalValue = inventoryManager.calculateTotalInventoryValue()
        val expectedValue = (999.99 * 5) + (499.99 * 10) + (299.99 * 0) + (199.99 * 3)
        Assertions.assertEquals(expectedValue, totalValue)
    }

    // Find most expensive product test case:
    // Success when the most expensive product is "Laptop"
    @Test
    fun findMostExpensiveProductTest() {
        val mostExpensiveProduct = inventoryManager.findMostExpensiveProduct()
        Assertions.assertEquals("Laptop", mostExpensiveProduct)
    }

    // Check if a product is in stock test case:
    // Success when the product "Smartphone" is in stock
    @Test
    fun isProductInStockTest() {
        val isInStock = inventoryManager.isProductInStock("Smartphone")
        Assertions.assertTrue(isInStock)
    }

    // Check if a product is not in stock test case:
    // Success when the product "Tablet" is not in stock (Because quantity is 0)
    @Test
    fun isProductNotInStockTest() {
        val isInStock = inventoryManager.isProductInStock("Tablet")
        Assertions.assertFalse(isInStock)
    }

    // Sort products by price with ascending order test case:
    // Success when the first product is "Smartwatch" and the last product is "Laptop"
    @Test
    fun sortProductsWithPriceAscendingTest() {
        inventoryManager.sortProducts("price", true)
        val sortedProducts = inventoryManager.getProducts()
        Assertions.assertEquals("Smartwatch", sortedProducts.first().name)
        Assertions.assertEquals("Laptop", sortedProducts.last().name)
    }

    // Sort products by price with descending order test case:
    // Success when the first product is "Laptop" and the last product is "Smartwatch"
    @Test
    fun sortProductsWithPriceDescendingTest() {
        inventoryManager.sortProducts("price", false)
        val sortedProducts = inventoryManager.getProducts()
        Assertions.assertEquals("Laptop", sortedProducts.first().name)
        Assertions.assertEquals("Smartwatch", sortedProducts.last().name)
    }

    // Sort products by quantity with ascending order test case:
    // Success when the first product is "Tablet" and the last product is "Smartphone"
    @Test
    fun sortProductsithQuantityAscendingTest() {
        inventoryManager.sortProducts("quantity", true)
        val sortedProducts = inventoryManager.getProducts()
        Assertions.assertEquals("Tablet", sortedProducts.first().name)
        Assertions.assertEquals("Smartphone", sortedProducts.last().name)
    }

    // Sort products by quantity with descending order test case:
    // Success when the first product is "Smartphone" and the last product is "Tablet"
    @Test
    fun sortProductsWithQuantityDescendingTest() {
        inventoryManager.sortProducts("quantity", false)
        val sortedProducts = inventoryManager.getProducts()
        Assertions.assertEquals("Smartphone", sortedProducts.first().name)
        Assertions.assertEquals("Tablet", sortedProducts.last().name)
    }
}
