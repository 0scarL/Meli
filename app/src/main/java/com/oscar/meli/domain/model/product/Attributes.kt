package com.oscar.meli.domain.model.product

import com.oscar.meli.data.model.product.AttributesDto

data class Attributes(
    val id: String?,
    val name: String?,
    val value: String?
)

fun AttributesDto.toDomain() : Attributes = Attributes(id, name, value)

fun List<AttributesDto>.toDomain() : List<Attributes> = map { it.toDomain() }
