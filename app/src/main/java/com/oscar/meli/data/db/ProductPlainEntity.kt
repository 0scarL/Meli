package com.oscar.meli.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oscar.meli.domain.model.product.ProductPlain

@Entity(tableName = "product_table")
data class ProductPlainEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "brand")
    val brand: String?,

    @ColumnInfo(name = "image_url")
    val url: String?,

    @ColumnInfo(name = "is_favorite")
    val favorite: Boolean = false
)

fun ProductPlain.toEntity() = ProductPlainEntity(id!!, name, brand, url, favorite)

fun List<ProductPlain>.toEntityList(): List<ProductPlainEntity> = map { product -> product.toEntity() }