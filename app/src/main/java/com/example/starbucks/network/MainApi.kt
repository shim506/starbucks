package com.example.starbucks.network

import com.example.starbucks.network.dto.HomeInfoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val mainNetworkModule = module {
    single { MainApi.createMainApi() }
}

interface MainApi {

    @GET(".")
    suspend fun getMainInfo(): Response<HomeInfoDto>

    companion object {
        private const val baseUrl = "https://api.codesquad.kr/starbuckst/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun createMainApi(): MainApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(MainApi::class.java)
        }
    }

}