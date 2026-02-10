package com.example.kotlin_getback_compose_lab1.data_class

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)
data class ProductResponse(
    val products: List<Product>
)



