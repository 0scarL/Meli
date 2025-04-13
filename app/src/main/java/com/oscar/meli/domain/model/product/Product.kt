package com.oscar.meli.domain.model.product

import com.oscar.meli.data.model.api.product.ProductDto

data class Product(
    val id: String,
    val name: String?,
    val attributes: List<Attributes>,
    val pictures: List<Pictures>


)

fun ProductDto.toDomain() = Product(
    id,
    name,
    attributes.map { it.toDomain() },
    pictures.map { it.toDomain() }
)





