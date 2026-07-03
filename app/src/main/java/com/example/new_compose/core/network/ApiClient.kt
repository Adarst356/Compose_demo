package com.example.new_compose.core.network

import com.example.new_compose.core.common.CommonRes
import com.example.new_compose.modules.dashboard.data.ProductListRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("products")
    suspend fun getProducts(): Response<ProductListRes>


}