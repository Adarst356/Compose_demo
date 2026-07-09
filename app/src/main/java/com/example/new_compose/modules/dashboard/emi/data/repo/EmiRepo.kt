package com.example.new_compose.modules.dashboard.emi.data.repo

import android.Manifest
import androidx.annotation.RequiresPermission
import com.example.new_compose.core.network.ApiClient
import com.example.new_compose.core.network.UiState
import com.example.new_compose.core.utils.NetworkManager
import com.example.new_compose.core.utils.getError
import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import javax.inject.Inject

class EmiRepo @Inject constructor(
    private val networkManager: NetworkManager,
    private val apis: ApiClient
) {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend fun getPhoto(
        callback: (UiState<List<EmiResponse>>) -> Unit
    ) {
        if (!networkManager.isNetworkAvailable()) {
            callback(UiState.Error("No Internet Connection"))
            return
        }
        callback(UiState.Loading)
        try {
            val response = apis.getPhoto()
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
