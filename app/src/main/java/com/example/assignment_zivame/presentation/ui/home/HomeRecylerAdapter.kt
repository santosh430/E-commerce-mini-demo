package com.example.assignment_zivame.presentation.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment_zivame.databinding.AdapterHomeRecyclerBinding
import com.example.assignment_zivame.networkcalls.dataclassapi.Product

class HomeRecylerAdapter(private val homeActivity: HomeActivity) : RecyclerView.Adapter<HomeRecylerAdapter.ViewHolder>() {

    var list= mutableListOf<Product>()

    inner class ViewHolder(val binding: AdapterHomeRecyclerBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            AdapterHomeRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(homeActivity)
            .load(list[position].image_url)
            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text=list[position].name
        holder.binding.tvProductPrice.text=list[position].price
        holder.binding.tvProductRating.rating= list[position].rating.toFloat()

        holder.binding.btnAddToCart.setOnClickListener {
            homeActivity.addItemToCart(list[position])
            Toast.makeText(homeActivity, "Item Added to Cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun playerList(list1:MutableList<Product>){
        list.clear()
        list=list1
        Log.d("myTAG","list in adapter $list1")
        notifyDataSetChanged()

    }

}
