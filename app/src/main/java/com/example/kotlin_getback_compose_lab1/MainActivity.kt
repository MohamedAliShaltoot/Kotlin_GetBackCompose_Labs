package com.example.kotlin_getback_compose_lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import com.example.kotlin_getback_compose_lab1.data_class.Product
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var dao: ProductDao
    private var products by mutableStateOf<List<Product>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dao = ProductDatabase.getDB(this).productDao()

        lifecycleScope.launch {
            loadProducts()
        }

        setContent {
            MainScreen(products)
        }
    }

    private suspend fun loadProducts() {

        if (NetworkUtil.isConnected(this)) {
            val response = RetrofitInstance.api.getProducts().products
            dao.insertAll(response)
            products = response
        } else {
            products = dao.getAll()
        }
    }
}
@Composable
fun MainScreen(products: List<Product>) {

    var selectedProductId by rememberSaveable { mutableStateOf<Int?>(null) }

    val selectedProduct = products.find { it.id == selectedProductId }

    val isLandscape =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    BackHandler(enabled = selectedProduct != null && !isLandscape) {
        selectedProductId = null
    }

    if (isLandscape) {

        Row(Modifier.fillMaxSize()) {

            ProductList(
                products = products,
                onClick = { selectedProductId = it.id },
                modifier = Modifier.weight(1f)
            )

            selectedProduct?.let {
                ProductDetails(
                    product = it,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    } else {

        if (selectedProduct == null) {

            ProductList(
                products = products,
                onClick = { selectedProductId = it.id }
            )

        } else {

            ProductDetails(
                product = selectedProduct,
              //  onBack = { selectedProductId = null }
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductList(
    products: List<Product>,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier) {

        items(products) { product ->

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onClick(product) }
            ) {

                GlideImage(
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(product.title)
                    Spacer(Modifier.height(6.dp))
                    Text(product.description)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetails(
    product: Product,
    modifier: Modifier = Modifier,
//    onBack: (() -> Unit)? = null
) {

    Column(modifier.padding(36.dp)) {

//        onBack?.let {
//            Text(
//                "Back",
//                modifier = Modifier
//                    .clickable { it() }
//                    .padding(bottom = 8.dp),
//                color = Color.Blue
//            )
//        }

        GlideImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(text = product.title)

        Spacer(Modifier.height(8.dp))

        Text(text = product.description)
    }
}





