package com.example.new_compose.modules.dashboard.emi.data.repo

import com.example.new_compose.core.network.ApiClient
import com.example.new_compose.core.utils.NetworkManager
import javax.inject.Inject

class ProfileRepo @Inject constructor(
    private val networkManager: NetworkManager,
    private val apis: ApiClient
) {


}
