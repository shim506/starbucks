package com.example.starbucks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbucks.data.model.Product
import com.example.starbucks.data.repository.RemoteDataSource
import com.example.starbucks.network.MainApi
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.StarbucksApi
import com.example.starbucks.network.dto.HomeInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repo = RemoteDataSource(MainApi.create(), StarbucksApi.create())

    private val _recommendFlow = MutableStateFlow<List<Product>?>(null)
    val recommendFlow: StateFlow<List<Product>?> = _recommendFlow

    private val _homeInfo = MutableStateFlow<NetworkResult<HomeInfoDto>>(NetworkResult.Loading())
    val homeInfo: StateFlow<NetworkResult<HomeInfoDto>> = _homeInfo

    init {
        viewModelScope.launch {
            repo.getHomeInfo().collect {
                _homeInfo.value = it
            }
        }
    }

    fun getRecommends(recommendIndexList: List<String>) {
        viewModelScope.launch {
            val imageList =
                MutableList<String?>(recommendIndexList.size) { null }
            val titleList =
                MutableList<String?>(recommendIndexList.size) { null }

            launch(Dispatchers.IO) {
                recommendIndexList.forEachIndexed { idx, value ->
                    val responseImage = repo.getRecommendImage(value)
                    val responseTitle = repo.getRecommendTittle(value)

                    if (responseImage is NetworkResult.Success) imageList[idx] = responseImage.data
                    if (responseTitle is NetworkResult.Success) {
                        titleList[idx] = responseTitle.data
                    }
                }
            }.join()

            val resultList = mutableListOf<Product>()
            recommendIndexList.forEachIndexed { idx, _ ->
                if (imageList[idx] != null && titleList[idx] != null) {
                    resultList.add(Product(titleList[idx]!!, imageList[idx]!!))
                }
            }
            _recommendFlow.value = resultList
        }

    }

}

