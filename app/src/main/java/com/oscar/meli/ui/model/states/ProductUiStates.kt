package com.oscar.meli.ui.model.states

import com.oscar.meli.ui.model.detail.DetailPlainVm
import com.oscar.meli.ui.model.product.ProductPlainVm

sealed class ProductUiStates {
    object Loading : ProductUiStates()
    data class Success(val products: List<ProductPlainVm>) : ProductUiStates()
    data class Error(val message: String) : ProductUiStates()
}