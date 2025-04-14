package com.oscar.meli.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oscar.meli.data.model.db.DetailEntity

@Dao
interface DetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: DetailEntity)

    @Query("SELECT * FROM detail_table WHERE id = :id")
    suspend fun getDetailById(id: String): DetailEntity

    @Query("Delete FROM detail_table WHERE id = :id")
    suspend fun deleteDetail(id: String)

}