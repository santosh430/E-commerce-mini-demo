package com.example.assignment_zivame.presentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment_zivame.data.repo.ProductRepository

class MainViewModelFactory(private val repository: ProductRepository)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, application = Application()) as T
    }
}