package com.example.new_compose.core.network

import com.example.new_compose.core.common.ApiResponse
import com.example.new_compose.core.common.CommonRes
import com.example.new_compose.modules.dashboard.data.ProductListRes
import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiClient {

    @GET("products")
    suspend fun getProducts(): Response<ProductListRes>


    @GET("/photos")
    suspend fun getPhoto(
    ): Response<List<EmiResponse>>
}