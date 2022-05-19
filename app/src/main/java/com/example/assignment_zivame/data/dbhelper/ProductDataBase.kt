package com.example.assignment_zivame.data.dbhelper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDataBase: RoomDatabase() {

    abstract fun productDao():ProductDao

    companion object{
        @Volatile
        private var INSTANCE: ProductDataBase? =null

        fun getDataBase (context: Context): ProductDataBase {
            val tempInstance = INSTANCE

            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}