package com.oscar.meli.ui.viewmodel.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.data.db.ProductPlainEntity
import com.oscar.meli.domain.usecase.GetProductsUseCase
import com.oscar.meli.domain.usecase.InsertProductPlainUseCase
import com.oscar.meli.utils.constants.several.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val insertProductPlainUseCase: InsertProductPlainUseCase
) : ViewModel() {


    private val status = "active"
    private val siteId = "MCO"


    fun getProducts(query: String) {
        viewModelScope.launch {

           val productos = getProductsUseCase(TOKEN, status, siteId, query)
            Log.d("meli producs result= ", productos.toString())
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