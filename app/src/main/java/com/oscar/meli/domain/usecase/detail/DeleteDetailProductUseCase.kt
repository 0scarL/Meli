package com.oscar.meli.domain.usecase.detail

import com.oscar.meli.data.repository.detail.DetailRepository
import com.oscar.meli.data.repository.product.ProductRepository
import javax.inject.Inject

class DeleteDetailProductUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteDetail(id)
        productRepository.deleteProduct(id)
    }

}