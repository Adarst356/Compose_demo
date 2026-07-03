package com.example.new_compose.modules.dashboard.data

import android.Manifest
import androidx.annotation.RequiresPermission
import com.example.new_compose.core.common.CommonRes
import com.example.new_compose.core.network.ApiClient
import com.example.new_compose.core.network.UiState
import com.example.new_compose.core.utils.NetworkManager
import com.example.new_compose.core.utils.getError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class DashboardRepo @Inject constructor(
    private val api: ApiClient,
    private val networkManager: NetworkManager
) {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)


    suspend fun getProducts(
        callback: (UiState<ProductListRes>) -> Unit
    ) {
        if (!networkManager.isNetworkAvailable()) {
            callback(UiState.Error("No Internet Connection"))
            return
        }
        callback(UiState.Loading)
        withContext(Dispatchers.IO) {
            val response = api.getProducts()
            callback(
                if (response.isSuccessful && response.body() != null) {
                    UiState.Success(response.body()!!)
                } else {
                    UiState.Error(getError("message", response))
                }
            )
        }
    }


}