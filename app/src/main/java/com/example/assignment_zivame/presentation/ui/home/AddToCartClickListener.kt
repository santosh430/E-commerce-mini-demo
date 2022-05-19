package com.example.assignment_zivame.presentation.ui.home

import com.example.assignment_zivame.networkcalls.dataclassapi.Product

interface AddToCartClickListener {

   fun addItemToCart(productDetails: Product)

}