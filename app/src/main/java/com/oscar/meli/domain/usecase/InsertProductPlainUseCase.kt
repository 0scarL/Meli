package com.oscar.meli.domain.usecase

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.data.repository.product.ProductRepository
import javax.inject.Inject

class InsertProductPlainUseCase @Inject constructor(
    private val repository: ProductRepository){

    suspend operator fun invoke(products: List<ProductPlainEntity>) =
        repository.insertProducts(products)



}