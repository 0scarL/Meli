package com.oscar.meli.domain.model.product

import com.oscar.meli.data.model.api.product.ProductResultDto

data class ProductResult(
    val results: List<Product>
)

fun ProductResultDto.toDomain() : ProductResult = ProductResult(results.map { it.toDomain() })


