package com.oscar.meli.ui.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.domain.model.detalle.toDomain
import com.oscar.meli.domain.model.product.toDomain
import com.oscar.meli.domain.usecase.detail.DeleteDetailProductUseCase
import com.oscar.meli.domain.usecase.detail.GetProducDetailUseCase
import com.oscar.meli.domain.usecase.detail.InsertDetailUseCase
import com.oscar.meli.domain.usecase.product.DeleteProductUseCase
import com.oscar.meli.domain.usecase.product.InsertProductUseCase
import com.oscar.meli.ui.model.detail.DetailPlainVm
import com.oscar.meli.ui.model.detail.toVm
import com.oscar.meli.ui.model.product.ProductPlainVm
import com.oscar.meli.ui.model.states.DetailUiState
import com.oscar.meli.utils.constants.ProductOrigin
import com.oscar.meli.utils.constants.configuration.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertProductUseCase: InsertProductUseCase,
    private val getProductDetailUseCase: GetProducDetailUseCase,
    private val insertDetailUseCase: InsertDetailUseCase,
    private val deleteDetailProductUseCase: DeleteDetailProductUseCase
) : ViewModel() {
    private val _uiStateDetail = MutableLiveData<DetailUiState>()
    val uiStateDetail: LiveData<DetailUiState> = _uiStateDetail


    fun getProductDetail(id: String, isfavorite: Boolean) {
        viewModelScope.launch {
            _uiStateDetail.value = DetailUiState.Loading
            try {
                val result = getProductDetailUseCase.invoke(TOKEN, id, isfavorite)
                _uiStateDetail.value = DetailUiState.Success(result.toVm(), result.favorite)

            } catch (e: Exception) {
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }

        }
    }


    fun saveProduct(product: ProductPlainVm) {
        viewModelScope.launch {
            try {
                insertProductUseCase(product.toDomain())
            } catch (e: Exception) {
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }

        }
    }

    fun saveDetail(detail: DetailPlainVm) {
        viewModelScope.launch {
            try{
                insertDetailUseCase(detail.toDomain())
            }catch (e: Exception){
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }
        }
    }

    fun deleteProduct(id: String){
        viewModelScope.launch {
            try {
                deleteDetailProductUseCase(id)
            } catch (e: Exception) {
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }
        }

    }
}