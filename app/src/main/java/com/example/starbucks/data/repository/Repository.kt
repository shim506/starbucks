package com.example.starbucks.data.repository

import com.example.starbucks.data.model.Menu
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.dto.HomeInfoDto
import com.example.starbucks.network.dto.MenuDto
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RemoteDataSource(get(), get()) }
}

interface Repository {
    suspend fun getHomeInfo(): Flow<NetworkResult<HomeInfoDto>>

    suspend fun getRecommendImage(value: String): NetworkResult<String>
    suspend fun getRecommendTittle(value: String): NetworkResult<String>

    suspend fun getMenuDetail(url: String): NetworkResult<List<Menu.MenuDetail>>

}