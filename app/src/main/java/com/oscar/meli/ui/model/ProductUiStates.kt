package com.oscar.meli.ui.model

sealed class ProductUiStates {
    object Loading : ProductUiStates()
    data class Success(val products: List<ProductPlainVm>) : ProductUiStates()
    data class Error(val message: String) : ProductUiStates()
}