package com.example.assignment_zivame.presentation.viewmodel.cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.data.repo.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val repository: ProductRepository,
                    application: Application
)

    : AndroidViewModel(
    application
) {

    init {
        getProducts()   //calling this method to get the data stored in database as soon as this class
                        //gets initialized.
    }

    private var products = MutableLiveData<List<ProductEntity>>()
    var productLiveData: LiveData<List<ProductEntity>> = products   // live data variable which gets data back from the mutable live data to observe in CartFragment.


    private fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch{

            val productData= repository.getProductsFromDatabase() //variable to get the data from the database
            Log.d("productData", productData.toString())
            products.postValue(productData)   //getting the data stored in mutable list
            Log.d("products", products.value.toString())
        }
    }

}