package com.oscar.meli.ui.model

import com.oscar.meli.domain.model.ProductDomain

data class ProductsVm(
    val id: String?,
    val name: String?,
    val brand: String?,
    val url: String?
)

fun ProductDomain.toVm() : ProductsVm {
    return ProductsVm(id, name, brand, url)
}
