package com.oscar.meli.domain.model.detalle

import com.oscar.meli.data.model.api.detalle.DetailDto
import com.oscar.meli.domain.model.product.Attributes
import com.oscar.meli.domain.model.product.Pictures
import com.oscar.meli.domain.model.product.toDomain

data class Detail(
    val id: String,
    val name: String,
    val idCatalogo: String,
    val status: String,
    val idDomain: String,
    val permalink: String,
    val family: String,
    val pictures: List<Pictures>,
    val description: Description?,
    val attributes: List<Attributes>
)

fun DetailDto.toDomain() = Detail(id,
    name,
    idCatalogo,
    status,
    idDomain,
    permalink,
    family,
    pictures.map { it.toDomain() },
    description?.toDomain(),
    attributes.map { it.toDomain() }
)





