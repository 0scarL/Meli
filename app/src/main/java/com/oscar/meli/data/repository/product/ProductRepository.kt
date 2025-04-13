package com.oscar.meli.data.repository.product

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.data.model.api.product.ProductResultDto
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(
        token: String, status: String, siteId: String, query: String
    ): ProductResultDto

    suspend fun insertProduct(product: ProductPlainEntity)

    fun getFavoriteProducts(): Flow<List<ProductPlainEntity>>

    suspend fun deleteProduct(id : String)


    suspend fun getLocalId(): Flow<List<String>>


}