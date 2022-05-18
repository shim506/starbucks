package com.example.starbucks.ui.order

import androidx.lifecycle.ViewModel
import com.example.starbucks.data.model.Product
import com.example.starbucks.data.repository.Repository
import com.example.starbucks.ui.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val orderViewModelModule = module {
    viewModel { OrderViewModel(get()) }
}

class OrderViewModel(repository: Repository) : ViewModel() {
    private val _menuSelected = MutableStateFlow<Menu>(Menu.DRINK)
    val menuSelected: StateFlow<Menu> = _menuSelected



}