package com.oscar.meli.data.datasource.api.detail

import com.oscar.meli.data.client.retrofit.RetrofitConstant.QUERY_DETAIL
import com.oscar.meli.data.model.api.detalle.DetailDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DetailApiDataSource {

   @GET(QUERY_DETAIL)
   suspend fun getProductDetail(@Header("Authorization") token: String,
                                @Path("productId") productId: String): DetailDto

}