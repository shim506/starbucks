package com.example.starbucks.ui

import androidx.lifecycle.ViewModel
import com.example.starbucks.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _recommendFlow = MutableStateFlow<List<Product>?>(null)
    val recommendFlow: StateFlow<List<Product>?> = _recommendFlow


}