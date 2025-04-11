package com.oscar.meli.ui.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oscar.meli.R
import com.oscar.meli.databinding.FragmentProductsBinding
import com.oscar.meli.ui.viewmodel.products.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    lateinit var binding : FragmentProductsBinding
    private val viewModel : ProductsViewModel by viewModels()

    companion object {
        val fragment = ProductsFragment()

        fun getProductsFragment() = fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()
        //setListeners()
    }

    private fun getProducts() {
        viewModel.getProducts("balon")
    }

    private fun setListeners() {
        TODO("Not yet implemented")
    }


}