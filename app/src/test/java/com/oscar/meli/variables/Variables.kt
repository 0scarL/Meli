package com.oscar.meli.variables

import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.domain.model.product.ProductPlain
import com.oscar.meli.ui.model.product.ProductPlainVm

object Variables {
    val exampleDetailFav = DetailPlain(
        id = "12345",
        name = "Smartphone Galaxy Z",
        idCatalogo = "cat-2023",
        status = "active",
        idDomain = "electronics",
        family = "mobile",
        url = "https://example.com/images/galaxy_z.jpg",
        description = "Latest foldable smartphone with cutting-edge features.",
        brand = "Samsung",
        favorite = true,
        permalink = "smartphone-galaxy-z"
    )

    val exampleDetailNoFav = DetailPlain(
        id = "123456",
        name = "Smartphone Galaxy Z",
        idCatalogo = "cat-2023",
        status = "active",
        idDomain = "electronics",
        family = "mobile",
        url = "https://example.com/images/galaxy_z.jpg",
        description = "Latest foldable smartphone with cutting-edge features.",
        brand = "Samsung",
        favorite = false,
        permalink = "smartphone-galaxy-z"
    )

    val exampleProductPlainEntity = listOf(
        ProductPlainEntity(
            id = "e001",
            name = "Audífonos Inalámbricos",
            brand = "Sony",
            url = "https://example.com/images/audifonos_sony.jpg",
            favorite = true
        ),
        ProductPlainEntity(
            id = "e002",
            name = "Mochila Urbana",
            brand = "Puma",
            url = "https://example.com/images/mochila_puma.jpg",
            favorite = false
        ),
        ProductPlainEntity(
            id = "e003",
            name = "Gafas de Sol",
            brand = "Ray-Ban",
            url = "https://example.com/images/gafas_rayban.jpg",
            favorite = true
        )
    )

    val exampleForInsert = DetailPlain(
        id = "1qpwowie",
        name = "Producto X",
        idCatalogo = "cat-01",
        status = "active",
        idDomain = "dom-01",
        family = "familia-abc",
        url = "https://example.com/producto.jpg",
        description = "Un producto de prueba",
        brand = "Marca X",
        favorite = true,
        permalink = "producto-x"
    )

    val exampleDetail =
        DetailPlain(
            id = "001",
            name = "Smartphone Samsung Galaxy",
            idCatalogo = "cat-1001",
            status = "active",
            idDomain = "dom-2001",
            family = "Electronics",
            url = "https://example.com/samsung.jpg",
            description = "Latest Samsung Galaxy smartphone with AMOLED display",
            brand = "Samsung",
            favorite = true,
            permalink = "samsung-galaxy-2023"
        )

    val exampleProdcut = ProductPlainVm(
    id = "e003",
    name = "Gafas de Sol",
    brand = "Ray-Ban",
    url = "https://example.com/images/gafas_rayban.jpg",
    favorite = true
    )



}