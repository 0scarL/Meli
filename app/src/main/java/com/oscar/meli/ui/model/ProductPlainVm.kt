package com.oscar.meli.ui.model

import com.oscar.meli.domain.model.product.Product
import com.oscar.meli.domain.model.product.ProductPlain


data class ProductPlainVm(
    val id: String?,
    val name: String?,
    val brand: String?,
    val url: String?
)

fun List<Product>.toVmList(): List<ProductPlainVm> = map { product -> product.toVm() }


fun Product.toVm(): ProductPlainVm {
    val brand = attributes.firstOrNull { attribute -> attribute.id == "BRAND" }?.value
    val imageUrl = pictures.firstOrNull()?.url

    return ProductPlainVm(
        id = id,
        name = name,
        brand = brand,
        url = imageUrl
    )
}

fun ProductPlain.toVm() = ProductPlainVm(id, name, brand, url)

//fun List<ProductPlain>.toVmList() = map { it.toVm() }





