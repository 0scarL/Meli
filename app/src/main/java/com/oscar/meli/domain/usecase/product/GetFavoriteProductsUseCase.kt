package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.domain.model.product.ProductPlain
import com.oscar.meli.domain.model.product.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository

) {

    operator fun invoke(): Flow<List<ProductPlain>> = flow {
        productRepository.getFavoriteProducts()
            .collect{ products -> emit(products.map{it.toDomain()})}
        }
    }





