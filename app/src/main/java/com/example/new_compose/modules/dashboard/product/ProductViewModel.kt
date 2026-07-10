package com.example.new_compose.modules.dashboard.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.new_compose.core.network.UiState
import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import com.example.new_compose.modules.dashboard.product.data.model.ProductResponse
import com.example.new_compose.modules.dashboard.product.data.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repo: ProductRepo
) : ViewModel() {
    private val _productState = MutableStateFlow<UiState<ProductResponse>>(UiState.None)
    val productState: StateFlow<UiState<ProductResponse>> = _productState.asStateFlow()

    init {
        getProduct()
    }


    fun getProduct() {
        viewModelScope.launch {
            repo.getProduct {
                _productState.value = it
            }
        }
    }

}
