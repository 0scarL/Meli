package com.oscar.meli.data.model.api.product

data class ProductDto(
    val id: String,
    val name: String?,
    val attributes: List<AttributesDto>,
    val pictures: List<PicturesDto>
)
