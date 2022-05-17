package com.example.starbucks.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbucks.LogCollector
import com.example.starbucks.data.model.Product
import com.example.starbucks.data.repository.RemoteDataSource
import com.example.starbucks.network.MainApi
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.StarbucksApi
import com.example.starbucks.network.dto.HomeInfoDto
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

    fun getRecommends() {
        viewModelScope.launch {
            //Log.d()
        }

    }

}

