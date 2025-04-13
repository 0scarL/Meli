package com.oscar.meli.data.model.api.detalle

import com.google.gson.annotations.SerializedName
import com.oscar.meli.data.model.api.product.AttributesDto
import com.oscar.meli.data.model.api.product.PicturesDto

data class DetailDto(

    val id: String,
    val name: String,
    @SerializedName("catalog_product_id")
    val idCatalogo: String,
    val status: String,
    @SerializedName("domain_id")
    val idDomain: String,
    val permalink: String,
    @SerializedName("family_name")
    val family: String,
    val pictures: List<PicturesDto>,
    @SerializedName("short_description")
    val description: DescriptionDto?,
    val attributes: List<AttributesDto>
)

