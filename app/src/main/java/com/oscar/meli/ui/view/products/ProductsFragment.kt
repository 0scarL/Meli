package com.oscar.meli.ui.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.oscar.meli.databinding.FragmentProductsBinding
import com.oscar.meli.ui.model.ProductPlainVm
import com.oscar.meli.ui.model.ProductUiStates
import com.oscar.meli.ui.view.products.adapter.ProductAdapter
import com.oscar.meli.ui.viewmodel.products.ProductsViewModel
import com.oscar.meli.utils.constants.UiConstants.MJS_ERROR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private val adapter: ProductAdapter by lazy { ProductAdapter(emptyList()) }

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
        setAdapter()
        getProducts()
        setProductObservers()
        //setListeners()
    }

    private fun setAdapter() {
        binding.productAdapter.adapter = this.adapter
    }


    private fun setProductObservers() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer { states ->
            when (states) {
                is ProductUiStates.Loading -> {
                    setOnLoading()
                }

                is ProductUiStates.Success -> {
                    setOffLoading()
                    updateAdapter(states.products)
                }

                is ProductUiStates.Error -> {
                    setOffLoading()
                    showErrorMessage(states.message)
                }

            }


        })
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, MJS_ERROR + "$message", Toast.LENGTH_SHORT).show()
    }

    private fun updateAdapter(products: List<ProductPlainVm>) {
        val newList = products
        this.adapter.updateList(newList)
    }

    private fun getProducts() {
        viewModel.getProducts("balon")
    }


    private fun setOnLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setOffLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setListeners() {

    }


}