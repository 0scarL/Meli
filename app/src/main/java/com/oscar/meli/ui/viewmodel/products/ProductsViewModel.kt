package com.oscar.meli.ui.viewmodel.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.domain.usecase.product.DeleteProductUseCase
import com.oscar.meli.domain.usecase.product.GetFavoriteProductsUseCase
import com.oscar.meli.domain.usecase.product.GetLocalIdUseCase
import com.oscar.meli.domain.usecase.product.GetProductsUseCase
import com.oscar.meli.ui.model.product.toVm
import com.oscar.meli.ui.model.states.ProductUiStates
import com.oscar.meli.utils.constants.configuration.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val getLocalIdUseCase: GetLocalIdUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductUiStates>()
    val uiState: LiveData<ProductUiStates> = _uiState

    private val _uiStateFavorite = MutableLiveData<ProductUiStates>()
    val uiStateFavorite: LiveData<ProductUiStates> = _uiStateFavorite
    lateinit var idFavorites: MutableList<String?>

    private val status = "active"
    private val siteId = "MCO"


    fun getProducts(query: String) {
        viewModelScope.launch {
            _uiState.value = ProductUiStates.Loading
            try {
                val plainResult = getProductsUseCase(TOKEN, status, siteId, query)
                Log.d("meli", plainResult.toString())
                plainResult.forEach { product ->
                    if (idFavorites.contains(product.id)) {
                        product.favorite = true
                    }
                }
                _uiState.value = ProductUiStates.Success(plainResult)
                showLog(plainResult.toString())
            } catch (e: Exception) {
                _uiState.value = ProductUiStates.Error(e.message.toString())
            }

        }

    }

    fun getFavoriteProducts() {
        viewModelScope.launch {
            _uiStateFavorite.value = ProductUiStates.Loading
            getFavoriteProductsUseCase.invoke()
                .catch { _uiStateFavorite.value = ProductUiStates.Error(it.message.toString()) }
                .map { products -> products.map { it.toVm() } }
                .flowOn(Dispatchers.IO)
                .collect { product ->
                    val favorites = ProductUiStates.Success(product)
                    _uiStateFavorite.postValue(favorites)
                    showLog(favorites.toString())
                }


        }
    }


    fun deleteFavoriteProduct(id: String) {
        viewModelScope.launch {
            try {
                deleteProductUseCase(id)
            } catch (e: Exception) {
                _uiState.value = ProductUiStates.Error(e.message.toString())
            }

        }

    }

    fun getLocalId() {
        viewModelScope.launch {
            getLocalIdUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { id -> idFavorites = id.toMutableList() }
        }
    }

    private fun showLog(listaVm: String) {
        Log.d("meli resultado api", listaVm)
    }


}