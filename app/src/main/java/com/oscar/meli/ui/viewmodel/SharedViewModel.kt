package com.oscar.meli.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.ui.model.product.ProductPlainVm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * ViewModel compartido entre diferentes fragments o componentes.
 * Se utiliza para mantener y compartir datos comunes como el producto seleccionado
 * o el texto de búsqueda actual, sin necesidad de pasarlos directamente entre fragments.
 */
@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _selectedProduct = MutableLiveData<ProductPlainVm>()
    val selectedProduct: LiveData<ProductPlainVm?> = _selectedProduct

    private val _productToSearch = MutableLiveData<String>()
    val productToSearch: LiveData<String?> = _productToSearch

    fun selectProduct(product: ProductPlainVm) {
        _selectedProduct.value = product

    }

    fun productToSearch(product: String) {
        _productToSearch.value = product
    }
}