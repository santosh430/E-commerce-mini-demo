package com.example.assignment_zivame.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_zivame.ProductApplication
import com.example.assignment_zivame.R
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.presentation.viewmodel.home.HomeViewModel
import com.example.assignment_zivame.presentation.viewmodel.home.MainViewModelFactory

class HomeActivity : AppCompatActivity() {
    lateinit var repository: ProductRepository
    lateinit var mViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // initializing viewModel
        repository  = ( applicationContext as ProductApplication).productRepository
        mViewModel = ViewModelProvider(this,
            MainViewModelFactory(repository))[HomeViewModel::class.java]

        mViewModel.productLiveData.observe({ lifecycle }, {
            Log.d("TAG",it.toString())
        })

    }
}