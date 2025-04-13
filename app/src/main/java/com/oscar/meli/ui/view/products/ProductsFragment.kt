package com.oscar.meli.ui.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.oscar.meli.MainActivity
import com.oscar.meli.databinding.FragmentProductsBinding
import com.oscar.meli.ui.model.product.ProductPlainVm
import com.oscar.meli.ui.model.states.ProductUiStates
import com.oscar.meli.ui.view.detail.DetailFragment
import com.oscar.meli.ui.view.detail.DetailFragment.Companion.getDetailFragmentInstance
import com.oscar.meli.ui.view.products.adapter.ProductAdapter
import com.oscar.meli.ui.viewmodel.SharedViewModel
import com.oscar.meli.ui.viewmodel.products.ProductsViewModel
import com.oscar.meli.utils.constants.UiConstants.MJS_ERROR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    companion object {

        fun getProductsFragmentInstance() = ProductsFragment()
    }

    lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val adapter: ProductAdapter by lazy { ProductAdapter(emptyList()) }


    private val deleteFavoriteProduct: (ProductPlainVm) -> Unit =
        { product -> deleteFavoriteProduct(product) }

    private val selectedProduct: (ProductPlainVm) -> Unit =
        { product -> getSelectedProduct(product) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getLocalProductId()
        setProducToSearchObserver()
        setProductObserver()
        setFavoriteObserver()
        setListeners()
    }

    private fun setAdapter() {
        binding.productAdapter.adapter = this.adapter
        this.adapter.setDeleteFavoriteProduct(deleteFavoriteProduct)
        this.adapter.setSelectedProducts(selectedProduct)
    }

    private fun setProducToSearchObserver() {
        sharedViewModel.productToSearch.observe(viewLifecycleOwner, Observer { product ->
            if (product != null) {
                getProducts(product)
            }
        })
    }


    private fun setProductObserver() {
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

    private fun setFavoriteObserver() {
        viewModel.uiStateFavorite.observe(viewLifecycleOwner, Observer { favStates ->
            when (favStates) {
                is ProductUiStates.Loading -> {
                    setOnLoading()
                }

                is ProductUiStates.Success -> {
                    setOffLoading()
                    updateAdapter(favStates.products)
                }

                is ProductUiStates.Error -> {
                    setOffLoading()
                    showErrorMessage(favStates.message)
                }
            }
        })
    }

    private fun deleteFavoriteProduct(favoriteProduct: ProductPlainVm) {
        if (favoriteProduct.favorite) {
            favoriteProduct.id?.let { viewModel.deleteFavoriteProduct(favoriteProduct.id) }
        }
    }

    private fun getSelectedProduct(selectedProduct: ProductPlainVm) {
        sharedViewModel.selectProduct(selectedProduct)
        launchDetailFragment(getDetailFragmentInstance())

    }

    private fun launchDetailFragment(fragment: DetailFragment) {
        (activity as? MainActivity)?.fragmentSelector(fragment)
//        val fragment = DetailFragment.getDetailFragment()
//        val bundle = Bundle()
//        bundle.putSerializable("selectedProduct", selectedProduct)
//        fragment.arguments = bundle
//        (activity as? MainActivity)?.fragmentSelector(fragment)

    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context, MJS_ERROR + "$message", Toast.LENGTH_SHORT).show()
    }

    private fun updateAdapter(products: List<ProductPlainVm>) {
        val newList = products
        this.adapter.updateList(newList)
    }

    private fun getProducts(productToSearch: String) {
        viewModel.getProducts(productToSearch)
    }


    private fun setOnLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setOffLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setListeners() {
        binding.buttonGetFavorite.setOnClickListener { v -> getFavorites() }
    }

    private fun getFavorites() {
        viewModel.getFavoriteProducts()
    }

    private fun getLocalProductId(){
        viewModel.getLocalId()
    }




}