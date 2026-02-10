package com.example.kotlin_getback_compose_lab1

import android.R.attr.onClick
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_getback_compose_lab1.data_class.ListItem
import com.example.kotlin_getback_compose_lab1.data_class.Product



import androidx.compose.runtime.*

import androidx.work.*
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {

    private val productsState = mutableStateOf<List<Product>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startWorker()
        observeWorker()

        setContent {
            MyLazyList(productsState.value)
        }
    }

    private fun startWorker() {
        val request = OneTimeWorkRequestBuilder<ProductWorker>()
            .addTag("ProductWorker")
            .build()

        WorkManager.getInstance(this).enqueue(request)
    }

    private fun observeWorker() {
        WorkManager.getInstance(this)
            .getWorkInfosByTagLiveData("ProductWorker")
            .observe(this) { workInfos ->
                val data = workInfos.firstOrNull()?.outputData?.getString("products")
                data?.let {
                    val type = object : TypeToken<List<Product>>() {}.type
                    val products: List<Product> = Gson().fromJson(it, type)
                    productsState.value = products
                }
            }
    }

    @Composable
    fun MyLazyList(products: List<Product>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ListRow(product)
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun ListRow(product: Product) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp).clickable(
                    onClick = {
                        Toast.makeText(this, product.title, Toast.LENGTH_SHORT).show()
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = product.title)
                Text(
                    text = product.description,
                    color = Color.Gray
                )
            }
        }
    }
}



