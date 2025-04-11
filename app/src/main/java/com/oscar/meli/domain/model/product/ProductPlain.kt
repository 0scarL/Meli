package com.oscar.meli.domain.model.product

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.ui.model.ProductPlainVm

data class ProductPlain(
    val id: String?,
    val name: String?,
    val brand: String?,
    val url: String?
)

fun ProductPlainVm.toDomain() = ProductPlain(id, name, brand, url)

fun ProductPlainEntity.toDomain() = ProductPlain(id, name, brand, url)

//fun List<ProductPlainEntity>.toDomainList() = map { product -> product.toDomain() }

fun List<ProductPlainVm>.toDomainList() = map { product -> product.toDomain() }
