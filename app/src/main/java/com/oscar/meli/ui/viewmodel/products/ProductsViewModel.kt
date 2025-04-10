package com.oscar.meli.ui.viewmodel.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.data.client.retrofit.RetrofitConstant
import com.oscar.meli.data.client.retrofit.RetrofitHelper
import com.oscar.meli.data.datasource.products.ProductsDataSource
import com.oscar.meli.utils.constants.several.TOKEN
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    fun getProducts(query: String) {
        viewModelScope.launch {
            val retrofit = RetrofitHelper.getRetrofit(RetrofitConstant.URL_BASE)
            val respuesta = retrofit.create(ProductsDataSource::class.java)
                .getProducts(TOKEN, "active", "MCO", "balon")
            Log.d("meli producs result= ", respuesta.toString())

        }
    }

}