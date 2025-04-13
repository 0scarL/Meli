package com.oscar.meli.data.datasource.api.product

import com.oscar.meli.data.client.retrofit.RetrofitConstant
import com.oscar.meli.data.model.api.product.ProductResultDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductApiDataSource {

    @GET(RetrofitConstant.QUERY_PRODUCTS)
    suspend fun getProducts(@Header("Authorization") token: String,
        @Query("status") status: String,
        @Query("site_id") siteId: String,
        @Query("q") query: String
    ): ProductResultDto

}