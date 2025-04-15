package com.oscar.meli.data.repository.product

import com.oscar.meli.data.datasource.api.product.ProductApiDataSource
import com.oscar.meli.data.datasource.db.DetailDao
import com.oscar.meli.data.datasource.db.ProductDao
import com.oscar.meli.data.db.ProductPlainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementación del repositorio de productos.
 * Este repositorio maneja la lógica de acceso a datos relacionados con productos,
 * tanto desde una fuente de datos remota (API) como local (base de datos Room).
 *
 * @param productDataSource Fuente de datos remota para obtener productos desde la API.
 * @param productDao DAO para el acceso a datos locales relacionados con productos.
 */
class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductApiDataSource,
    private val productDao: ProductDao,

    ) : ProductRepository {


    /**
     * Obtiene una lista de productos desde la API.
     *
     * @param token Token de autenticación.
     * @param status Estado del producto.
     * @param siteId ID del sitio o país (ej. MLA, MCO).
     * @param query Término de búsqueda.
     * @return Lista de productos obtenida desde la API.
     */
    override suspend fun getProducts(token: String, status: String, siteId: String, query: String) =
        withContext(Dispatchers.IO) { productDataSource.getProducts(token, status, siteId, query) }


    /**
     * Inserta un producto en la base de datos local.
     *
     * @param product Producto en formato de entidad que se desea almacenar localmente.
     */
    override suspend fun insertProduct(product: ProductPlainEntity) {
        withContext(Dispatchers.IO) { productDao.insertProduct(product) }
    }

    /**
     * Elimina un producto de la base de datos local por su ID.
     *
     * @param id Identificador único del producto a eliminar.
     */
    override suspend fun deleteProduct(id: String) {
        withContext(Dispatchers.IO) {
            productDao.deleteProduct(id)

        }
    }

    /**
     * Obtiene un flujo (Flow) de productos marcados como favoritos desde la base de datos local.
     *
     * @return Flow con la lista de productos favoritos.
     */
    override fun getFavoriteProducts(): Flow<List<ProductPlainEntity>> = flow {
        emit(productDao.getFavoriteProducts())
    }

    /**
     * Obtiene un flujo (Flow) con los IDs de productos almacenados localmente.
     *
     * @return Flow con la lista de IDs locales.
     */
    override suspend fun getLocalId(): Flow<List<String>> = flow {
        emit(productDao.getLocalId())


    }
}