package com.example.starbucks.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbucks.data.model.Menu
import com.example.starbucks.data.repository.Repository
import com.example.starbucks.network.NetworkResult
import com.example.starbucks.network.dto.MenuDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailViewModelModule = module {
    viewModel { MenuDetailViewModel(get()) }
}

class MenuDetailViewModel(private val repository: Repository) : ViewModel() {
    private val _detailFlow =
        MutableStateFlow<NetworkResult<List<Menu.MenuDetail>>>(NetworkResult.Loading())
    val detailFlow: StateFlow<NetworkResult<List<Menu.MenuDetail>>> = _detailFlow


    fun getMenuDetail(url: String) {
        viewModelScope.launch {
            _detailFlow.value = repository.getMenuDetail(url)
        }
    }
}