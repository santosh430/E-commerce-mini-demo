package com.example.assignment_zivame.data.dbhelper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product:ProductEntity)

    @Query("SELECT * FROM product_table ")
    suspend fun getProduct():LiveData<ProductEntity>

}