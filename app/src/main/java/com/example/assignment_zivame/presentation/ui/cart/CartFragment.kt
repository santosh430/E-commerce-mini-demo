package com.example.assignment_zivame.presentation.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_zivame.ProductApplication
import com.example.assignment_zivame.R
import com.example.assignment_zivame.data.dbhelper.ProductEntity
import com.example.assignment_zivame.data.repo.ProductRepository
import com.example.assignment_zivame.databinding.FragmentCartBinding
import com.example.assignment_zivame.presentation.ui.loader.LoaderFragment
import com.example.assignment_zivame.presentation.viewmodel.cart.CartViewModel
import com.example.assignment_zivame.presentation.viewmodel.cart.MainViewModelFactoryCart

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var mAdapter: CartRecyclerAdapter
    private lateinit var repository: ProductRepository
    private lateinit var mViewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater)

        //initializing viewModel
        repository  = ( requireContext().applicationContext as ProductApplication).productRepository
        mViewModel = ViewModelProvider(this,
            MainViewModelFactoryCart(repository)
        )[CartViewModel::class.java]

        //setting recycler view
        binding.rvCart.apply {
            layoutManager=LinearLayoutManager(context)
            mAdapter= CartRecyclerAdapter(this@CartFragment)
            adapter=mAdapter
        }

        //observing live data
        mViewModel.productLiveData.observe(viewLifecycleOwner){

            if(it != null)
            {
                Log.d("TAG","CartFragment livedata $it")
                mAdapter.productListInCart(it as MutableList<ProductEntity>)
                binding.btnCheckout.isVisible=true
                binding.tvNoItems.isInvisible=true
                binding.rvCart.isVisible=true
            }
            else{

                binding.btnCheckout.isInvisible=true
                binding.tvNoItems.isVisible=true
                binding.rvCart.isInvisible=true

                Log.d("TAG","CartFragment livedata is null $it")

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckout.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.cartFragment,LoaderFragment())?.commit()
        }
    }


}