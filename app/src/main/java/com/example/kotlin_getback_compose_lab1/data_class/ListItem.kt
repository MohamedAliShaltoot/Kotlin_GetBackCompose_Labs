package com.example.kotlin_getback_compose_lab1.data_class

import androidx.annotation.DrawableRes

data class ListItem(
    val title: String,
    val subtitle: String,
  @DrawableRes  val imageRes: Int,
    val contentDescription: String,

)

