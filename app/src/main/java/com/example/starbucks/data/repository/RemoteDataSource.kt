package com.example.starbucks.data.repository

import android.util.Log
import com.example.starbucks.data.model.Product
import com.example.starbucks.network.MainApi
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.StarbucksApi
import com.example.starbucks.network.dto.HomeInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RemoteDataSource(private val mainApi: MainApi, val starbucksApi: StarbucksApi) : Repository {
    override suspend fun getRecommends(): Flow<NetworkResult<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeInfo(): Flow<NetworkResult<HomeInfoDto>> {
        return try {
            val response = mainApi.getMainInfo()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Log.d("TAG", "sucess")
                flow { emit(NetworkResult.Success(body)) }
            } else {
                Log.d("TAG", "error")
                flow { emit(NetworkResult.Error(response.code(), response.message())) }
            }
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
            flow { emit(NetworkResult.Exception(e)) }
        }
    }

}