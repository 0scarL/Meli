package com.oscar.meli.data.datasource.products

import com.oscar.meli.data.model.productos.ProductsList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductsDataSource {

    @GET("products/search")
    suspend fun getProducts(@Header("Authorization") token: String,
        @Query("status") status: String,
        @Query("site_id") siteId: String,
        @Query("q") query: String
    ): ProductsList

}