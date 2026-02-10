package com.example.kotlin_getback_compose_lab1

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson


class ProductWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        return try {


            val response = kotlinx.coroutines.runBlocking {
                RetrofitInstance.api.getProducts()
            }

            val json = Gson().toJson(response.products)

            Result.success(
                workDataOf("products" to json)
            )

        } catch (e: Exception) {
            Result.failure()
        }
    }
}