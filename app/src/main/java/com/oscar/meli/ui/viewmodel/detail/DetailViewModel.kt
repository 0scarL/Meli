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
import com.oscar.meli.ui.model.states.ProductUiStates
import com.oscar.meli.utils.constants.ProductOrigin
import com.oscar.meli.utils.constants.UiConstants.LABEL_ERROR
import com.oscar.meli.utils.constants.UiConstants.MSJ_NET_ERROR
import com.oscar.meli.utils.constants.configuration.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel encargado de gestionar la lógica de negocio relacionada con los detalles de un producto.
 * Se comunica con los casos de uso para obtener, guardar o eliminar información.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertProductUseCase: InsertProductUseCase,
    private val getProductDetailUseCase: GetProducDetailUseCase,
    private val insertDetailUseCase: InsertDetailUseCase,
    private val deleteDetailProductUseCase: DeleteDetailProductUseCase
) : ViewModel() {
    private val _uiStateDetail = MutableLiveData<DetailUiState>()
    val uiStateDetail: LiveData<DetailUiState> = _uiStateDetail

    /**
     * Obtiene los detalles de un producto desde el repositorio correspondiente.
     *
     * @param id ID del producto a buscar.
     * @param isfavorite Si es `true`, se busca el producto en la base de datos local.
     */

    fun getProductDetail(id: String, isfavorite: Boolean) {
        viewModelScope.launch {
            _uiStateDetail.value = DetailUiState.Loading
            try {
                val result = getProductDetailUseCase.invoke(TOKEN, id, isfavorite)
                _uiStateDetail.value = DetailUiState.Success(result.toVm(), result.favorite)

            } catch (e: IOException) {
                _uiStateDetail.value = DetailUiState.Error(MSJ_NET_ERROR)
            }catch (e: Exception) {
                _uiStateDetail.value = DetailUiState.Error(LABEL_ERROR + e.message.toString())
            }

        }
    }


    /**
     * Guarda un producto como favorito en la base de datos local.
     *
     * @param product Producto a guardar.
     */
    fun saveProduct(product: ProductPlainVm) {
        viewModelScope.launch {
            try {
                insertProductUseCase(product.toDomain())
            } catch (e: Exception) {
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }

        }
    }

    /**
     * Guarda el detalle de un producto en la base de datos local.
     *
     * @param detail Detalle del producto.
     */
    fun saveDetail(detail: DetailPlainVm) {
        viewModelScope.launch {
            try{
                insertDetailUseCase(detail.toDomain())
            }catch (e: Exception){
                _uiStateDetail.value = DetailUiState.Error(e.message.toString())
            }
        }
    }


    /**
     * Elimina un producto de la base de datos local.
     *
     * @param id ID del producto a eliminar.
     */
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