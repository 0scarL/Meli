package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.db.toEntity
import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.domain.model.product.ProductPlain
import javax.inject.Inject

class InsertProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(product: ProductPlain) =
        repository.insertProduct(product.toEntity())


}