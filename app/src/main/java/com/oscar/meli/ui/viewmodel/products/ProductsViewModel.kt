package com.oscar.meli.ui.viewmodel.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.domain.usecase.GetProductsUseCase
import com.oscar.meli.domain.usecase.InsertProductPlainUseCase
import com.oscar.meli.ui.model.ProductUiStates
import com.oscar.meli.utils.constants.several.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val insertProductPlainUseCase: InsertProductPlainUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductUiStates>()
    val uiState : LiveData<ProductUiStates> = _uiState

    private val status = "active"
    private val siteId = "MCO"


    fun getProducts(query: String) {
        viewModelScope.launch {
           _uiState.value = ProductUiStates.Loading
            try {
                val plainResult = getProductsUseCase(TOKEN, status, siteId, query)
                _uiState.postValue(ProductUiStates.Success(plainResult))
            }catch (e : Exception){
                _uiState.postValue(ProductUiStates.Error(e.message.toString()))
            }

            insertProductPlainUseCase(productosEjemplo)


        }

    }

    val productosEjemplo = listOf(
        ProductPlainEntity(
            id = "ML12345",
            name = "Auriculares Bluetooth",
            brand = "Sony",
            url = "https://http2.mlstatic.com/D_NQ_NP_12345-MLA.jpg"
        ),
        ProductPlainEntity(
            id = "ML67890",
            name = "Smartphone Galaxy S23",
            brand = "Samsung",
            url = "https://http2.mlstatic.com/D_NQ_NP_67890-MLA.jpg"
        ),
        ProductPlainEntity(
            id = "ML54321",
            name = "Notebook Inspiron 15",
            brand = "Dell",
            url = "https://http2.mlstatic.com/D_NQ_NP_54321-MLA.jpg"
        )
    )





}