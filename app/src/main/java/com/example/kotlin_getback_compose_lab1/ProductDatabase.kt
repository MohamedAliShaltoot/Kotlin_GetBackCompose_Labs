package com.example.kotlin_getback_compose_lab1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_getback_compose_lab1.data_class.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDB(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ProductDatabase::class.java,
                    "product_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
