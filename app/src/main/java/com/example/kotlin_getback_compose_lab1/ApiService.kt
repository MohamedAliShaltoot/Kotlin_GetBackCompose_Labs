package com.example.kotlin_getback_compose_lab1

import com.example.kotlin_getback_compose_lab1.data_class.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}
