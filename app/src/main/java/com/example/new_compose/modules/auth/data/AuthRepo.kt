package com.example.new_compose.modules.auth.data

import com.example.new_compose.core.network.ApiClient
import com.example.new_compose.core.utils.NetworkManager
import javax.inject.Inject

class AuthRepo @Inject constructor(
    private val networkManager: NetworkManager,
    private val apis: ApiClient
)
{

}
