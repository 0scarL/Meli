package com.oscar.meli.domain.model.detalle

import com.oscar.meli.data.model.api.detalle.DescriptionDto

data class Description(
    val content: String?
)

fun DescriptionDto.toDomain() : Description = Description(content)
