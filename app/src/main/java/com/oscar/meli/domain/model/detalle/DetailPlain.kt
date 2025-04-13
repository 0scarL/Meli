package com.oscar.meli.domain.model.detalle

import com.oscar.meli.data.model.db.DetailEntity
import com.oscar.meli.data.model.api.detalle.DetailDto
import com.oscar.meli.ui.model.detail.DetailPlainVm

data class DetailPlain(
    val id: String,
    val name: String,
    val idCatalogo: String,
    val status: String,
    val idDomain: String,
    val family: String,
    val url: String?,
    val description: String?,
    val brand: String?,
    val favorite: Boolean = false,
    val permalink: String
)



fun DetailDto.toPlainDomain(): DetailPlain {
    val brand = attributes.firstOrNull { attribute -> attribute.id == "BRAND" }?.value
    val imageUrl = pictures.firstOrNull()?.url
    val content = description?.content.toString()

    return DetailPlain(
        id = id,
        name = name,
        idCatalogo = idCatalogo,
        status = status,
        idDomain = idDomain,
        family = family,
        url = imageUrl,
        description = content,
        brand = brand,

        permalink = permalink
    )
}

fun DetailPlainVm.toDomain() = DetailPlain(id, name, idCatalogo, status, idDomain, family, url, description, brand, favorite, permalink)

fun DetailEntity.toPlainDomain() = DetailPlain(id, name, idCatalogo, status, idDomain, family, url, description, brand, favorite, permalink)
