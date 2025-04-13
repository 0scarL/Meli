package com.oscar.meli.domain.model.product

import com.oscar.meli.data.model.api.product.PicturesDto

data class Pictures(
    val id: String?,
    val url: String?
)

fun PicturesDto.toDomain() = Pictures(id, url)

fun List<PicturesDto>.toDomain() = map { it.toDomain() }