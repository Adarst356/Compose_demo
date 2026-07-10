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




}