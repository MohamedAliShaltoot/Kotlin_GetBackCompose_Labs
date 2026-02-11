package com.example.kotlin_getback_compose_lab1.data_class

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Product(
//    val id: Int,
//    val title: String,
//    val description: String,
//    val thumbnail: String
//)
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)

data class ProductResponse(
    val products: List<Product>
)



