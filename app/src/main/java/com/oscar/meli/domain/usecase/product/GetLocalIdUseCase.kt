package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalIdUseCase @Inject constructor(
    private val productRepository: ProductRepository) {

    suspend operator fun invoke() :Flow<List<String>> =
        productRepository.getLocalId()

    }


