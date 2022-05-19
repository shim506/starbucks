package com.example.starbucks.data.repository

import android.util.Log
import com.example.starbucks.data.model.Menu
import com.example.starbucks.network.MainApi
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.StarbucksApi
import com.example.starbucks.network.StarbucksApi.Companion.baseUrl
import com.example.starbucks.network.dto.HomeInfoDto
import com.example.starbucks.network.dto.MenuDto
import com.example.starbucks.network.dto.menuItemDtoToMenuDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val mainApi: MainApi, private val starbucksApi: StarbucksApi) :
    Repository {


    override suspend fun getHomeInfo(): Flow<NetworkResult<HomeInfoDto>> {
        return try {
            val response = mainApi.getMainInfo()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                flow { emit(NetworkResult.Success(body)) }
            } else {
                flow { emit(NetworkResult.Error(response.code(), response.message())) }
            }
        } catch (e: Exception) {
            flow { emit(NetworkResult.Exception(e)) }
        }
    }

    override suspend fun getRecommendImage(value: String): NetworkResult<String> {
        val response = starbucksApi.getRecommendImage(value.toLong())
        val body = response.body()
        try {
            if (response.isSuccessful) {
                body?.let {
                    return NetworkResult.Success(baseUrl + it.file[0].file_PATH)
                }
            }
            return NetworkResult.Error(response.code(), response.message())

        } catch (e: Throwable) {
            return NetworkResult.Exception(e)
        }
    }


    override suspend fun getRecommendTittle(value: String): NetworkResult<String> {
        val response = starbucksApi.getRecommendTittle(value.toLong())
        val body = response.body()
        try {
            if (response.isSuccessful) {
                body?.let {
                    it.view?.let {
                        return NetworkResult.Success(it.product_NM)
                    }
                }
            }
            return NetworkResult.Error(response.code(), response.message())
        } catch (e: Throwable) {
            return NetworkResult.Exception(e)
        }
    }

    override suspend fun getMenuDetail(url: String): NetworkResult<List<Menu.MenuDetail>> {
        val response = starbucksApi.getMenuDetail(url)
        val body = response.body()
        try {
            if (response.isSuccessful && body != null) {
                return NetworkResult.Success(body.list.map { it.menuItemDtoToMenuDetail() })
            }
            return NetworkResult.Error(response.code(), response.message())
        } catch (e: Throwable) {
            return NetworkResult.Exception(e)
        }
    }


}