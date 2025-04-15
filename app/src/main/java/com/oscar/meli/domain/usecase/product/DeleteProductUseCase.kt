package com.oscar.meli.domain.usecase.product

import com.oscar.meli.data.repository.product.ProductRepository
import javax.inject.Inject
/**
 * Caso de uso que se encarga de eliminar un producto.
 * Utiliza el repositorio de productos para realizar la operación de eliminación.
 *
 * @param repository El repositorio de productos que maneja las operaciones relacionadas con productos.
 */
class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteProduct(id)
    }

}