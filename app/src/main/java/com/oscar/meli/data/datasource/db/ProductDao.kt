package com.oscar.meli.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oscar.meli.data.db.ProductPlainEntity

@Dao
interface ProductDao {
    
    @Query("SELECT * FROM product_table ORDER BY id DESC")
    suspend fun getAllProducts(): List<ProductPlainEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductPlainEntity)

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun deleteProduct(id: String)

    @Query("SELECT * FROM product_table ")
    fun getFavoriteProducts(): List<ProductPlainEntity>

    @Query("SELECT ID FROM product_table")
    fun getLocalId(): List<String>


}