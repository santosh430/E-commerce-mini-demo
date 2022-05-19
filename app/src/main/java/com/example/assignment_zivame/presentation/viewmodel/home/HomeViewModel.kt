package com.example.assignment_zivame.presentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.networkcalls.dataclassapi.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository,
    application: Application)

    :AndroidViewModel(
    application
) {

    init {
        getProducts()   //calling this method to hit the api as soon as HomeViewModel class
                        //gets initialized.
    }

    private var products =MutableLiveData<List<Product>>()
    var productLiveData:LiveData<List<Product>> = products   // live data variable which gets data back from the mutable live data to observe in home activity.


    private fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch{

            val productData= repository.getResultFromApi().body()?.products //variable to store the data that comes from api
            products.postValue(productData)   //getting the data stored in mutable list
        }
    }

}