package com.example.assignment_zivame.data.repo

import com.example.assignment_zivame.data.dbhelper.ProductDataBase
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.networkcalls.InterfaceAPI
import com.example.assignment_zivame.networkcalls.RetrofitHelper
import com.example.assignment_zivame.networkcalls.dataclassapi.DataClassAPI
import retrofit2.Response

class ProductRepository(private val database:ProductDataBase,
                        private val retroFit:RetrofitHelper) {


    suspend fun getResultFromApi():Response<DataClassAPI>{
        val newsApi = retroFit.getInstance().create(InterfaceAPI::class.java)

        return newsApi.getProductsFromApi()
    }

    suspend fun addProductsToDatabase(products:ProductEntity){
        database.productDao().addProductToDatabase(products)
    }

    fun getProductsFromDatabase():List<ProductEntity>{
        return database.productDao().getProductFromDatabase()
    }



}