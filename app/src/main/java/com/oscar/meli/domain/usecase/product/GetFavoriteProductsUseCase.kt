package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import com.oscar.meli.domain.model.product.ProductPlain
import com.oscar.meli.domain.model.product.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
/**
 * Caso de uso que se encarga de obtener los productos favoritos.
 * Utiliza el repositorio de productos para recuperar la lista de productos marcados como favoritos.
 *
 * @param productRepository El repositorio de productos que maneja las operaciones relacionadas con productos.
 */
class GetFavoriteProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository

) {

    /**
     * Obtiene una lista de productos favoritos.
     *
     * Este método recolecta los productos favoritos del repositorio y los convierte al dominio adecuado.
     * Los productos son transformados desde su representación en la base de datos (o fuente de datos)
     * a su forma de dominio utilizando el método `toDomain()`.
     *
     * @return Un flujo (`Flow`) de una lista de productos favoritos en su formato de dominio.
     */
    operator fun invoke(): Flow<List<ProductPlain>> = flow {
        productRepository.getFavoriteProducts()
            .collect{ products -> emit(products.map{it.toDomain()})}
        }
    }





