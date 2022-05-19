package com.example.assignment_zivame.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_zivame.ProductApplication
import com.example.assignment_zivame.R
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.databinding.ActivityHomeBinding
import com.example.assignment_zivame.networkcalls.dataclassapi.Product
import com.example.assignment_zivame.presentation.ui.cart.CartFragment
import com.example.assignment_zivame.presentation.viewmodel.home.HomeViewModel
import com.example.assignment_zivame.presentation.viewmodel.home.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(),AddToCartClickListener {

    private lateinit var repository: ProductRepository
    private lateinit var mViewModel: HomeViewModel
    private lateinit var binding:ActivityHomeBinding
    private lateinit var myAdapter:HomeRecylerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_home)
        supportActionBar?.hide()

        // initializing viewModel
        repository  = ( applicationContext as ProductApplication).productRepository
        mViewModel = ViewModelProvider(this,
            MainViewModelFactory(repository))[HomeViewModel::class.java]

        //setting recycler view
        setRecyclerView()

        // observing product data from api through viewModel
        mViewModel.productLiveData.observe({ lifecycle }, {
            Log.d("TAG",it.toString())

            myAdapter.productList(it as MutableList<Product>)
            binding.rvGadgets.adapter?.notifyDataSetChanged()
        })

        //making fragment transaction to CartFragment on click of imageview shopping cart.
        binding.ivCart.setOnClickListener {
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .add(R.id.homeActivity,CartFragment()).commit()
        }
    }

    private fun setRecyclerView() {
        binding.rvGadgets.apply {
            layoutManager= LinearLayoutManager(this@HomeActivity)
            myAdapter = HomeRecylerAdapter(this@HomeActivity)
            adapter=myAdapter
        }

    }

    //overridden method from interface clickListener
    // implemented to listen the click at the Add to Cart Button
    // and save clicked item to room database

    override fun addItemToCart(productDetails: Product) {

        Log.d("TAG",productDetails.toString())
        val product=ProductEntity(image_url = productDetails.image_url,
            name = productDetails.name, price = productDetails.price,
        rating = productDetails.rating)

        CoroutineScope(Dispatchers.IO).launch {
            repository.addProductsToDatabase(product)

        }
    }
}