package com.example.starbucks.ui.order

import com.example.starbucks.data.model.Menu
import com.example.starbucks.data.repository.LocalRepository
import com.example.starbucks.network.NetworkResult

interface ISelectedMenu {
    suspend fun getSelectedMenuItems(): NetworkResult<List<Menu>>
}

class FoodMenuSelected(private val repository: LocalRepository) : ISelectedMenu {
    override suspend fun getSelectedMenuItems(): NetworkResult<List<Menu>> {
        return repository.getFoodMenu()
    }

}

class DrinkMenuSelected(private val repository: LocalRepository) : ISelectedMenu {
    override suspend fun getSelectedMenuItems(): NetworkResult<List<Menu>> {
        return repository.getDrinkMenu()
    }
}

class ProductMenuSelected(private val repository: LocalRepository) : ISelectedMenu {
    override suspend fun getSelectedMenuItems(): NetworkResult<List<Menu>> {
        return repository.getProductMenu()
    }
}

enum class MenuEnum {
    DRINK, FOOD, PRODUCT
}