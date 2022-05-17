package com.example.starbucks.data.repository

import com.example.starbucks.data.model.Product
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.dto.HomeInfoDto
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getRecommends(): Flow<NetworkResult<Product>>

    suspend fun getHomeInfo(): Flow<NetworkResult<HomeInfoDto>>

    suspend fun getRecommendImage(value: String): NetworkResult<String>
    suspend fun getRecommendTittle(value: String): NetworkResult<String>


}