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


/**
 * Extensión de la clase `Product` para convertirla a un objeto `ProductPlainVm`.
 *
 * @return Un objeto `ProductPlainVm` que contiene los detalles del producto.
 */
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

/**
 * Extensión de la clase `DetailPlainVm` para convertirla a un objeto `ProductPlainVm`.
 *
 * @param isFavorite Valor opcional para marcar si el producto es favorito.
 * @return Un objeto `ProductPlainVm` con los detalles del producto.
 */
fun DetailPlainVm.toProductPlainVm(isFavorite: Boolean = false): ProductPlainVm {
    return ProductPlainVm(
        id = this.id,
        name = this.name,
        brand = this.brand,
        url = this.url,
        favorite = isFavorite
    )
}







