package com.example.starbucks.network

import com.example.starbucks.network.dto.MenuDto
import com.example.starbucks.network.dto.ProductFileInfoDto
import com.example.starbucks.network.dto.ProductViewInfoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

val starbucksNetworkModule = module {
    single { StarbucksApi.create() }
}

interface StarbucksApi {
    @FormUrlEncoded
    @POST("menu/productViewAjax.do")
    suspend fun getRecommendTittle(
        @Field("product_cd") productIdx: Long

    ): Response<ProductViewInfoDto>

    @FormUrlEncoded
    @POST("menu/productFileAjax.do")
    suspend fun getRecommendImage(
        @Field("PRODUCT_CD") productIdx: Long
    ): Response<ProductFileInfoDto>


    @POST("https://www.starbucks.co.kr/upload/json/menu/W0000171.js")
    suspend fun getMenuColdBrew(): Response<MenuDto>

    @POST("upload/json/menu/{menuPath}")
    suspend fun getMenuDetail(@Path("menuPath") url: String): Response<MenuDto>

    companion object {
        const val baseUrl = "https://www.starbucks.co.kr/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun create(): StarbucksApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(StarbucksApi::class.java)
        }
    }
}