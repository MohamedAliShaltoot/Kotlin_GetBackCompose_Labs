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


class MainActivity : ComponentActivity() {
    val items = listOf(
        ListItem("Android", "Jetpack Compose", R.drawable.ic_launcher_background,"Person"),
        ListItem("Kotlin", "Modern Language", R.drawable.ic_launcher_background,"Person"),
        ListItem("Mohamed", "Android Developer ", R.drawable.ic_launcher_background,"Person"),
        ListItem("Ahemd", "Flutter Developer", R.drawable.ic_launcher_background,"Person"),
        ListItem("Nada", "Flutter Developer", R.drawable.ic_launcher_background,"Person"),
        ListItem("Ahmed", "Flutter Developer", R.drawable.ic_launcher_background,"Person"),
        ListItem("Flutter", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
        ListItem("Compose", "Declarative UI", R.drawable.ic_launcher_background,"Person"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLazyList()
        }
    }
    @Composable
    fun MyLazyList() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                ListRow(item)
            }
        }
    }

    @Composable
    fun ListRow(item: ListItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.clickable(
                onClick={
                 Toast.makeText(this@MainActivity,"you clicked ${item.title}",Toast.LENGTH_SHORT).show()
                }
            )) {
                Text(
                    text = item.title,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = item.subtitle,
                    color = Color.Gray
                )
            }
        }
    }
    @Preview(showBackground = true)
    @Composable

    fun PreviewMyLazyList() {
        MyLazyList()
    }
}



