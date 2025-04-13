package com.oscar.meli.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oscar.meli.domain.model.detalle.DetailPlain

@Entity(tableName = "detail_table")
data class DetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "idCatalogo")
    val idCatalogo: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "idDomain")
    val idDomain: String,

    @ColumnInfo(name = "family")
    val family: String,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "brand")
    val brand: String?,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean,

    @ColumnInfo(name = "permalink")
    val permalink: String
)

fun DetailPlain.toEntity() = DetailEntity(id, name, idCatalogo, status, idDomain, family, url, description, brand, favorite, permalink)
