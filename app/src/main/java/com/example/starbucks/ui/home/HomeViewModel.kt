package com.example.starbucks.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbucks.data.model.Product
import com.example.starbucks.data.repository.Repository
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.dto.HomeInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _recommendFlow = MutableStateFlow<List<Product>?>(null)
    val recommendFlow: StateFlow<List<Product>?> = _recommendFlow

    private val _homeInfo = MutableStateFlow<NetworkResult<HomeInfoDto>>(NetworkResult.Loading())
    val homeInfo: StateFlow<NetworkResult<HomeInfoDto>> = _homeInfo

    init {
        viewModelScope.launch {
            repository.getHomeInfo().collect {
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

            recommendIndexList.forEachIndexed { idx, value ->
                launch(Dispatchers.IO) {
                    launch() {
                        val responseImage = repository.getRecommendImage(value)
                        if (responseImage is NetworkResult.Success) imageList[idx] =
                            responseImage.data
                    }
                    launch {
                        val responseTitle = repository.getRecommendTittle(value)
                        if (responseTitle is NetworkResult.Success) {
                            titleList[idx] = responseTitle.data
                        }
                    }
                }.join()
            }
            val resultList = mutableListOf<Product>()
            recommendIndexList.forEachIndexed { idx, _ ->
                if (imageList[idx] != null && titleList[idx] != null) {
                    resultList.add(Product(titleList[idx]!!, imageList[idx]!!))
                    _recommendFlow.value = resultList
                }

            }

        }

    }
}

