package com.oscar.meli.data.model.product

import com.google.gson.annotations.SerializedName

data class AttributesDto(
    val id: String?,
    val name: String?,
    @SerializedName("value_name")
    val value: String?
)
