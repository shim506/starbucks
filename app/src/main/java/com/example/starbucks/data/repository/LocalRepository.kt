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
            Menu.MenuCategory("콜드브루", "W0000171.js", subTitle = "Cold Brew"),
            Menu.MenuCategory("에스프레소", "W0000003.js", subTitle = "Espresso"),
            Menu.MenuCategory("프라푸치노", "W0000004.js", subTitle = "Frappuccino")
        )
        return NetworkResult.Success(list)
    }

    suspend fun getFoodMenu(): NetworkResult<List<Menu>> {
        val list = listOf<Menu>(
            Menu.MenuCategory("브레드", "W0000013.js", subTitle = "Bread"),
            Menu.MenuCategory("케이크", "W0000032.js", subTitle = "Cake"),
            Menu.MenuCategory("샌드위치 & 샐러드", "W0000033.js", subTitle = "Sandwich & Salad")
        )
        return NetworkResult.Success(list)
    }

    suspend fun getProductMenu(): NetworkResult<List<Menu>> {
        val list = listOf<Menu>(
            Menu.MenuCategory("머그", "W0000030.js", subTitle = "Mug"),
            Menu.MenuCategory("글라스", "W0000164.js", subTitle = "Glass"),
            Menu.MenuCategory("플라스틱 텀블러", "W0000031.js", subTitle = "Tumblr")
        )
        return NetworkResult.Success(list)
    }

}