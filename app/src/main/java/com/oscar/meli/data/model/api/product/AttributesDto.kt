package com.oscar.meli.data.model.api.product

import com.google.gson.annotations.SerializedName

data class AttributesDto(
    val id: String?,
    val name: String?,
    @SerializedName("value_name")
    val value: String?
)
