package com.oscar.meli.ui.model.detail

import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.ui.model.product.ProductPlainVm

data class DetailPlainVm(
    val id: String,
    val name: String,
    val idCatalogo: String,
    val status: String,
    val idDomain: String,
    val family: String,
    val url: String?,
    val description: String?,
    val brand: String?,
    var favorite: Boolean = false,
    val permalink: String
)


fun DetailPlain.toVm() = DetailPlainVm(id, name, idCatalogo, status, idDomain, family, url, description, brand, favorite, permalink)