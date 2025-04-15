package com.oscar.meli.ui.viewmodel.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.meli.domain.usecase.product.GetFavoriteProductsUseCase
import com.oscar.meli.domain.usecase.product.GetLocalIdUseCase
import com.oscar.meli.domain.usecase.product.GetProductsUseCase
import com.oscar.meli.ui.model.product.toVm
import com.oscar.meli.ui.model.states.ProductUiStates
import com.oscar.meli.utils.constants.UiConstants.LABEL_ERROR
import com.oscar.meli.utils.constants.UiConstants.MSJ_NET_ERROR
import com.oscar.meli.utils.constants.UiConstants.TAG_RESULT
import com.oscar.meli.utils.constants.configuration.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel encargado de gestionar la lógica de negocio relacionada con la obtención, almacenamiento
 * y gestión de productos, incluyendo productos favoritos y locales.
 */
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
    private val getLocalIdUseCase: GetLocalIdUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductUiStates>()
    val uiState: LiveData<ProductUiStates> = _uiState

    /**
     * LiveData que representa el estado actual de la UI para los productos favoritos.
     * Puede ser Loading, Success o Error.
     */

    private val _uiStateFavorite = MutableLiveData<ProductUiStates>()
    val uiStateFavorite: LiveData<ProductUiStates> = _uiStateFavorite
    var idFavorites: MutableList<String?> = mutableListOf()

    // Variables de configuración estáticas para la búsqueda de productos
    private val status = "active"
    private val siteId = "MCO"

    /**
     * Obtiene la lista de productos según la consulta proporcionada.
     * Marca los productos como favoritos si se encuentran en la lista `idFavorites`.
     *
     * @param query Texto de búsqueda utilizado para filtrar los productos.
     */
    fun getProducts(query: String) {
        viewModelScope.launch {
            _uiState.value = ProductUiStates.Loading
            try {
                val plainResult = getProductsUseCase(TOKEN, status, siteId, query)

                // Marca los productos como favoritos si están en la lista de favoritos
                plainResult.forEach { product ->
                    if (idFavorites.contains(product.id)) {
                        product.favorite = true
                    }
                }
                _uiState.value = ProductUiStates.Success(plainResult)
                showLog(plainResult.toString())
            } catch (e: IOException) {
                _uiState.value = ProductUiStates.Error(MSJ_NET_ERROR)
            } catch (e: Exception) {
                _uiState.value = ProductUiStates.Error(LABEL_ERROR + e.message.toString())
            }

        }

    }

    /**
     * Obtiene la lista de productos favoritos desde el repositorio.
     */
    fun getFavoriteProducts() {
        viewModelScope.launch {
            _uiStateFavorite.value = ProductUiStates.Loading
            getFavoriteProductsUseCase.invoke()
                .catch { _uiStateFavorite.value = ProductUiStates.Error(it.message.toString()) }
                .map { products -> products.map { it.toVm() } }
                .flowOn(Dispatchers.IO)
                .collect { product ->
                    val favorites = ProductUiStates.Success(product)
                    _uiStateFavorite.value = favorites
                    showLog(favorites.toString())
                }


        }
    }

    /**
     * Obtiene la lista de IDs locales desde el repositorio y la almacena en `idFavorites`.
     * este listado de ids se utiliza para validar que productos provenientes del api se
     * encuentran almacenados como favoritos.
     */
    fun getLocalId() {
        viewModelScope.launch {
            getLocalIdUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { id -> idFavorites = id.toMutableList() }
        }
    }

    private fun showLog(listaVm: String) {
        Log.d(TAG_RESULT, listaVm)
    }


}