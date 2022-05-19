package com.example.starbucks.data.model

sealed class Menu {
    data class MenuCategory(
        val title: String,
        val url: String,
        val image: String = tempImageUrl,
        val subTitle: String = "",
    ) : Menu() {
        companion object {
            const val tempImageUrl =
                "https://www.starbucks.co.kr//upload/store/skuimg/2021/04/[110569]_20210415143036138.jpg"
        }
    }

    data class MenuDetail(
        val title: String,
        val image: String,
        val url: String,
        val price: Int? = null
    ) : Menu()

}
