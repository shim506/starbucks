package com.example.starbucks.data.repository

import com.example.starbucks.data.model.Menu
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.StarbucksApi
import org.koin.dsl.module

val localRepositoryModule = module {
    single {
        LocalRepository(get())
    }
}

class LocalRepository(api: StarbucksApi) {
    suspend fun getDrinkMenu(): NetworkResult<List<Menu>> {
        val list = listOf<Menu>(
            Menu("콜드브루", "upload/json/menu/W0000171.js", subTitle = "Cold Brew"),
            Menu("에스프레소", "upload/json/menu/W0000003.js", subTitle = "Espresso"),
            Menu("프라푸치노", "upload/json/menu/W0000004.js", subTitle = "Frappuccino")
        )
        return NetworkResult.Success(list)
    }

    suspend fun getFoodMenu(): NetworkResult<List<Menu>> {
        val list = listOf<Menu>(
            Menu("브레드", "upload/json/menu/W0000013.js", subTitle = "Bread"),
            Menu("케이크", "upload/json/menu/W0000032.js", subTitle = "Cake"),
            Menu("샌드위치 & 샐러드", "upload/json/menu/W0000033.js", subTitle = "Sandwich & Salad")
        )
        return NetworkResult.Success(list)
    }

    suspend fun getProductMenu(): NetworkResult<List<Menu>> {
        val list = listOf<Menu>(
            Menu("머그", "upload/json/menu/W0000030.js", subTitle = "Mug"),
            Menu("글라스", "upload/json/menu/W0000164.js", subTitle = "Glass"),
            Menu("플라스틱 텀블러", "upload/json/menu/W0000031.js", subTitle = "Tumblr")
        )
        return NetworkResult.Success(list)
    }

}