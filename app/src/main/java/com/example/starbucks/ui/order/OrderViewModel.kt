package com.example.starbucks.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbucks.data.model.Menu
import com.example.starbucks.data.repository.LocalRepository
import com.example.starbucks.data.repository.Repository
import com.example.starbucks.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val orderViewModelModule = module {
    viewModel { OrderViewModel(get(), get()) }
}

class OrderViewModel(private val repository: Repository, private val localRepo: LocalRepository) :
    ViewModel() {
    private val _menuSelected =
        MutableStateFlow<ISelectedMenu>(DrinkMenuSelected(localRepo))
    val menuSelected = _menuSelected.debounce { 500 }

    private val _menuList = MutableStateFlow<NetworkResult<List<Menu>>>(NetworkResult.Loading())
    val menuList: StateFlow<NetworkResult<List<Menu>>> = _menuList
    
    init {
        getMenu(DrinkMenuSelected(localRepo))
    }

    fun getMenu(selected: ISelectedMenu) {
        viewModelScope.launch {
            _menuList.value = selected.getSelectedMenuItems()
        }
    }

    fun drinkSelected() {
        _menuSelected.value = DrinkMenuSelected(localRepo)
    }

    fun foodSelected() {
        _menuSelected.value = FoodMenuSelected(localRepo)
    }

    fun productSelected() {
        _menuSelected.value = ProductMenuSelected(localRepo)
    }


}