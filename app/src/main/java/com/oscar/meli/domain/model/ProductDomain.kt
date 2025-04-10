package com.oscar.meli.domain.model

import com.oscar.meli.data.model.productos.ProductDto

data class ProductDomain(
    val id: String?,
    val name: String?,
    val brand: String?,
    val url: String?
)

fun ProductDto.toDomain() : ProductDomain {
    val brand = attributes.firstOrNull { it.id == "BRAND" }?.value_name
    val url = pictures.firstOrNull()?.url
    return ProductDomain(id ?: "", name ?: "", brand, url)
}