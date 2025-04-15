package com.oscar.meli.ui.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.oscar.meli.MainActivity
import com.oscar.meli.databinding.FragmentSearchBinding
import com.oscar.meli.ui.view.products.ProductsFragment.Companion.getProductsFragmentInstance
import com.oscar.meli.ui.viewmodel.SharedViewModel
import com.oscar.meli.utils.constants.UiConstants.MJS_ERROR
import com.oscar.meli.utils.constants.UiConstants.MSJ_ERROR_EMPTY_SEARCH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {

        fun getSearchFragmentInstance() = SearchFragment()

    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        focusRequest()
        return binding.root
    }

    private fun focusRequest() {
        binding.editTextSearch.requestFocus()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchListener()
    }

    private fun setSearchListener() {
        binding.imageViewBuscar.setOnClickListener { v -> goToSearch()}
    }

    private fun goToSearch() {
        val product = binding.editTextSearch.text.toString().trim()

        if (product.isEmpty()) {
            showToast(MSJ_ERROR_EMPTY_SEARCH)
            return
        }

        launchDetailFragment(getProductsFragmentInstance())
        sharedViewModel.productToSearch(product)
    }

    private fun launchDetailFragment(getFragment: Fragment) {
        (activity as? MainActivity)?.fragmentSelector(getFragment)    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
