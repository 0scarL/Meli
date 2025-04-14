package com.oscar.meli.data.repository.detail

import com.oscar.meli.data.model.db.DetailEntity
import com.oscar.meli.domain.model.detalle.DetailPlain

interface DetailRepository {

    suspend fun getProductDetail(token: String, productId: String, isfavorite: Boolean): DetailPlain

    suspend fun insertDetail(detail: DetailEntity)

    suspend fun deleteDetail(id: String)

}