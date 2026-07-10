package com.example.new_compose.modules.dashboard.product.data.repo
import  com.example.new_compose.core.network.ApiClient
import com.example.new_compose.core.network.UiState
import com.example.new_compose.core.utils.NetworkManager
import com.example.new_compose.core.utils.getError
import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import com.example.new_compose.modules.dashboard.product.data.model.ProductResponse
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val networkManager: NetworkManager,
    private val apis: ApiClient
) {
    suspend fun getProduct(
        callback: (UiState<ProductResponse>) -> Unit
    ) {
        if (!networkManager.isNetworkAvailable()) {
            callback(UiState.Error("No Internet Connection"))
            return
        }
        callback(UiState.Loading)
        try {
            val response = apis.getProducts()
            callback(
                if (response.isSuccessful && response.body() != null) {
                    UiState.Success(response.body()!!)
                } else {
                    UiState.Error(
                        getError("message", response)
                    )
                }
            )
        } catch (e: Exception) {
            callback(UiState.Error(e.localizedMessage ?: "An unknown error occurred"))
        }
    }

}


