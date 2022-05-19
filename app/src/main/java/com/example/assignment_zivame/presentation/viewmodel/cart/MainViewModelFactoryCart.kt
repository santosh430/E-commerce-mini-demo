package com.example.assignment_zivame.presentation.viewmodel.cart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_zivame.data.repo.ProductRepository

class MainViewModelFactoryCart(private val repository: ProductRepository)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(repository, application = Application()) as T
    }
}