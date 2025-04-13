package com.oscar.meli.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oscar.meli.data.datasource.db.DetailDao
import com.oscar.meli.data.datasource.db.ProductDao
import com.oscar.meli.data.model.db.DetailEntity

@Database(entities = [ProductPlainEntity::class,
    DetailEntity::class], version = 1)
abstract class MeliDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    abstract fun getDetailDao() : DetailDao


}