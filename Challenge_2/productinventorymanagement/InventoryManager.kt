package com.example.productinventorymanagement


class InventoryManager(private var products: List<Product>) {
    // Function to get the list of products:
    fun getProducts(): List<Product> {
        return this.products
    }

    // Function to calculate total inventory value:
    fun calculateTotalInventoryValue(): Double {
        return products.sumOf { it.price * it.quantity }
    }

    // Function to find the most expensive product:
    fun findMostExpensiveProduct(): String? {
        return products.maxByOrNull { it.price }?.name
    }

    // Function to check if a product is in stock:
    fun isProductInStock(productName: String): Boolean {
        return products.any { it.name == productName && it.quantity > 0 }
    }

    // Function to sort products in sorting options (price, quantity) and sorting orders (ascending, descending):
    fun sortProducts(
        sortOptionBy: String,
        sortByAscendingOrder: Boolean
    ) {
        when(sortOptionBy) {
            "price" -> {
                if(sortByAscendingOrder) {
                    this.products = this.products.sortedBy {
                        product: Product -> product.price
                    }
                } else {
                    this.products = this.products.sortedByDescending {
                            product: Product -> product.price
                    }
                }
            }
            "quantity" -> {
                if(sortByAscendingOrder) {
                    this.products = this.products.sortedBy {
                            product: Product -> product.quantity
                    }
                } else {
                    this.products = this.products.sortedByDescending {
                            product: Product -> product.quantity
                    }
                }
            }
        }
    }

    // Function to display the inventory:
    fun displayInventory() {
        this.products.forEach {product: Product ->
            println("Product: ${product.name}, Quantity: ${product.quantity}, Price: ${product.price}")
        }
    }
}