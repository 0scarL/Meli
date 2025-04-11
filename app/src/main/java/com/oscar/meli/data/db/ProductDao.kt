package com.oscar.meli.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    
    @Query("SELECT * FROM product_table ORDER BY id DESC")
    suspend fun getAllProducts(): List<ProductPlainEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductPlainEntity>)
    

}