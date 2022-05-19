package com.example.assignment_zivame.data.dbhelper

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToDatabase(product:ProductEntity)

    @Query("select * from product_table")
    fun getProductFromDatabase():List<ProductEntity>

}