package com.example.assignment_zivame.data.dbhelper

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val image_url: String,
    val name: String,
    val price: String,
    val rating: Int
    )

