package com.oscar.meli.ui.model.states

import com.oscar.meli.ui.model.detail.DetailPlainVm

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val detail: DetailPlainVm, val favorite: Boolean) : DetailUiState()
    data class Error(val message: String) : DetailUiState()

}