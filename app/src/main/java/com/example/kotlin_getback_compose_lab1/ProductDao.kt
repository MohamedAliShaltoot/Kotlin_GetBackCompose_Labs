package com.example.kotlin_getback_compose_lab1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_getback_compose_lab1.data_class.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)
}
