package com.example.assignment_zivame.presentation.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.databinding.AdapterCartRecyclerBinding

class CartRecyclerAdapter(private val cartFragment: CartFragment) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    var list= mutableListOf<ProductEntity>()

    inner class ViewHolder(val binding: AdapterCartRecyclerBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            AdapterCartRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(cartFragment)
            .load(list[position].image_url)
            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text=list[position].name
        holder.binding.tvProductPrice.text=list[position].price

    }

    override fun getItemCount(): Int {
        Log.d("myTAGingetitem",list.toString())
        return list.size
    }


    fun productListInCart(list1:MutableList<ProductEntity>){
        list.clear()
        list=list1
        Log.d("myTAGinCartRecycler","list in adapter $list1")
        notifyDataSetChanged()

    }

}
