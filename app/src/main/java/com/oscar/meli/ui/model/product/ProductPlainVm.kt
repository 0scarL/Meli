package com.oscar.meli.ui.model.product

import com.oscar.meli.domain.model.product.Product
import com.oscar.meli.domain.model.product.ProductPlain
import com.oscar.meli.ui.model.detail.DetailPlainVm
import java.io.Serializable


data class ProductPlainVm(
    val id: String,
    val name: String?,
    val brand: String?,
    val url: String?,
    var favorite: Boolean = false
) : Serializable

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

fun ProductPlain.toVm() = ProductPlainVm(id, name, brand, url, favorite)


fun DetailPlainVm.toProductPlainVm(isFavorite: Boolean = false): ProductPlainVm {
    return ProductPlainVm(
        id = this.id,
        name = this.name,
        brand = this.brand,
        url = this.url,
        favorite = isFavorite
    )
}

//fun List<ProductPlain>.toVmList() = map { it.toVm() }





