package com.example.assignment_zivame

import android.app.Application
import com.example.assignment_zivame.data.dbhelper.ProductDataBase
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.networkcalls.RetrofitHelper

class ProductApplication: Application() {

    lateinit var productRepository: ProductRepository
    lateinit var productDatabase:ProductDataBase
    lateinit var retrofit: RetrofitHelper

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){

        productDatabase = ProductDataBase.getDataBase(applicationContext)
        productRepository = ProductRepository(productDatabase,retrofit )

    }

}