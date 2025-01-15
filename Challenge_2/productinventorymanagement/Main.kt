package com.example.productinventorymanagement

fun main() {
    // Inventory list:
    val products = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3)
    )

    // Create an instance of InventoryManager:
    val inventoryManager = InventoryManager(products)

    // Calculate total inventory value:
    val totalInventoryValue = inventoryManager.calculateTotalInventoryValue()
    println("Total Inventory Value: %.2f".format(totalInventoryValue))

    // Find the most expensive product:
    val mostExpensiveProduct = inventoryManager.findMostExpensiveProduct()
    println("Most Expensive Product: $mostExpensiveProduct")

    // Check if a product name "Headphones" is in stock:
    val isHeadphonesInStock = inventoryManager.isProductInStock("Headphones")
    println("Is Headphones in Stock: $isHeadphonesInStock")

    // Display the initial inventory:
    println("\nThe initial inventory:")
    inventoryManager.displayInventory()

    // Sort the inventory by price with ascending order:
    inventoryManager.sortProducts(
        sortOptionBy = "price",
        sortByAscendingOrder = true
    )

    // Display the inventory after sorting by price with ascending order:
    println("\nThe inventory after sorting by price with ascending order:")
    inventoryManager.displayInventory()

    // Sort the inventory by price with descending order:
    inventoryManager.sortProducts(
        sortOptionBy = "price",
        sortByAscendingOrder = false
    )

    // Display the inventory after sorting by price with descending order:
    println("\nThe inventory after sorting by price with descending order:")
    inventoryManager.displayInventory()

    // Sort the inventory by quantity with ascending order:
    inventoryManager.sortProducts(
        sortOptionBy = "quantity",
        sortByAscendingOrder = true
    )

    // Display the inventory after sorting by quantity with ascending order:
    println("\nThe inventory after sorting by quantity with ascending order:")
    inventoryManager.displayInventory()

    // Sort the inventory by quantity with descending order:
    inventoryManager.sortProducts(
        sortOptionBy = "quantity",
        sortByAscendingOrder = false
    )

    // Display the inventory after sorting by quantity with descending order:
    println("\nThe inventory after sorting by quantity with descending order:")
    inventoryManager.displayInventory()


}