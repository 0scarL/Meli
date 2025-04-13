package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteProduct(id)
    }

}