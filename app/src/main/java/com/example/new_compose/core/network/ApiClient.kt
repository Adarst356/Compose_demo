package com.example.new_compose.core.network

import com.example.new_compose.modules.dashboard.emi.data.model.EmiResponse
import com.example.new_compose.modules.dashboard.product.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiClient {

    @GET("/products")
    suspend fun getProducts(
        //@Body body: ProductRequest
    ): Response<ProductResponse>


    @GET("/photos")
    suspend fun getPhoto(
    ): Response<List<EmiResponse>>
}