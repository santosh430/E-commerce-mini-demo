package com.example.assignment_zivame.presentation.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_zivame.ProductApplication
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.databinding.FragmentCartBinding
import com.example.assignment_zivame.presentation.viewmodel.cart.CartViewModel
import com.example.assignment_zivame.presentation.viewmodel.cart.MainViewModelFactoryCart

class CartFragment : Fragment() {

    lateinit var binding:FragmentCartBinding
    lateinit var mAdapter: CartRecyclerAdapter
    lateinit var repository: ProductRepository
    lateinit var mViewModel:CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater)

        //initializing viewModel
        repository  = ( requireContext().applicationContext as ProductApplication).productRepository
        mViewModel = ViewModelProvider(this,
            MainViewModelFactoryCart(repository)
        )[CartViewModel::class.java]

        binding.rvCart.apply {
            layoutManager=LinearLayoutManager(context)
            mAdapter= CartRecyclerAdapter(this@CartFragment)
            adapter=mAdapter
        }

        mViewModel.productLiveData.observe(viewLifecycleOwner){
            if(it != null)
            {
                Log.d("TAG","CartFragment livedata $it")
                mAdapter.productListInCart(it as MutableList<ProductEntity>)
            }
            else{
                Log.d("TAG","CartFragment livedata null $it")

            }

            Log.d("TAG","CartFragment livedata inside $it")

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting recycler view



    }



}