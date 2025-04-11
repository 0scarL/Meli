package com.oscar.meli.data.repository.product

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.data.model.product.ProductResultDto

interface ProductRepository {

    suspend fun getProducts(
        token: String, status: String, siteId: String, query: String
    ): ProductResultDto

    suspend fun insertProducts(products: List<ProductPlainEntity>)

}