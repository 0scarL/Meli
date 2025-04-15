package com.oscar.meli.data.repository.detail

import com.oscar.meli.data.datasource.api.detail.DetailApiDataSource
import com.oscar.meli.data.datasource.db.DetailDao
import com.oscar.meli.data.model.db.DetailEntity
import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.domain.model.detalle.toPlainDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementación del repositorio de detalles de productos.
 * Esta clase actúa como intermediaria entre las fuentes de datos (API y base de datos local)
 * y el dominio de la aplicación, proporcionando métodos para obtener, insertar y eliminar detalles de productos.
 *
 * @param detailApiDataSource Fuente de datos remota para obtener detalles desde una API.
 * @param detailDao DAO para acceder a la base de datos local (Room).
 */
class DetailRepositoryImp @Inject constructor(
    private val detailApiDataSource: DetailApiDataSource,
    private val detailDao: DetailDao
) : DetailRepository {


    /**
     * Obtiene el detalle de un producto.
     *
     * Si el producto es favorito, se obtiene el detalle desde la base de datos local.
     * Si no, se realiza una llamada a la API para obtenerlo de forma remota.
     *
     * @param token Token de autenticación para la API.
     * @param productId ID del producto a consultar.
     * @param isfavorite Indica si el producto es un favorito (y por tanto está en la base de datos local).
     * @return [DetailPlain] Objeto con la información del producto en formato de dominio.
     */
    override suspend fun getProductDetail(
        token: String, productId: String, isfavorite: Boolean
    ): DetailPlain {
        return withContext(Dispatchers.IO) {
            if (isfavorite) {
                detailDao.getDetailById(productId).toPlainDomain()
            } else {
                detailApiDataSource.getProductDetail(token, productId).toPlainDomain()
            }
        }
    }

    /**
     * Inserta un detalle de producto en la base de datos local.
     *
     * @param detail Entidad [DetailEntity] a guardar.
     */
    override suspend fun insertDetail(detail: DetailEntity) {
        withContext(Dispatchers.IO) { detailDao.insertDetail(detail) }
    }

    /**
     * Elimina un detalle de producto de la base de datos local por su ID.
     *
     * @param id ID del producto a eliminar.
     */
    override suspend fun deleteDetail(id: String) {
        withContext(Dispatchers.IO) { detailDao.deleteDetail(id) }
    }
}
