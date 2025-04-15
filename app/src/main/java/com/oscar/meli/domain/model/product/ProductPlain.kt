package com.oscar.meli.domain.model.product

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.ui.model.product.ProductPlainVm

data class ProductPlain(
    val id: String,
    val name: String?,
    val brand: String?,
    val url: String?,
    val favorite: Boolean = false
)

fun ProductPlainVm.toDomain() = ProductPlain(id, name, brand, url, favorite)

fun ProductPlainEntity.toDomain() = ProductPlain(id, name, brand, url, favorite)


fun List<ProductPlainVm>.toDomainList() = map { product -> product.toDomain() }
