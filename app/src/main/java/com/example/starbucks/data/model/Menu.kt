package com.example.starbucks.data.model

data class Menu(
    val title: String,
    val url: String,
    val image: String = tempImageUrl
) {
    companion object {
        const val tempImageUrl =
            "https://www.starbucks.co.kr//upload/store/skuimg/2021/04/[110569]_20210415143036138.jpg"
    }
}
