package com.oscar.meli.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductPlainEntity::class], version = 1)
abstract class MeliDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao



}