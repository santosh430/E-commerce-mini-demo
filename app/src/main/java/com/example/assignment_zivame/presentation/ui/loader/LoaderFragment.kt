package com.example.assignment_zivame.presentation.ui.loader

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment_zivame.R
import com.example.assignment_zivame.presentation.ui.orderplaced.OrderPlacedFragment


class LoaderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
        activity?.supportFragmentManager?.beginTransaction()?.
            addToBackStack(null)?.
                replace(R.id.loaderFragment,OrderPlacedFragment())?.commit()
        },30000)


        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

}