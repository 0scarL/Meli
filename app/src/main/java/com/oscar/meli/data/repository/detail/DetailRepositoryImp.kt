package com.oscar.meli.data.repository.detail

import com.oscar.meli.data.datasource.api.detail.DetailApiDataSource
import com.oscar.meli.data.datasource.db.DetailDao
import com.oscar.meli.data.model.db.DetailEntity
import com.oscar.meli.domain.model.detalle.DetailPlain
import com.oscar.meli.domain.model.detalle.toPlainDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val detailApiDataSource: DetailApiDataSource,
    private val detailDao: DetailDao
) : DetailRepository {

    override suspend fun getProductDetail(token: String,productId: String,isfavorite: Boolean
    ): DetailPlain {
        return withContext(Dispatchers.IO) {
            if (isfavorite) {
                detailDao.getDetailById(productId).toPlainDomain()
            } else {
                detailApiDataSource.getProductDetail(token, productId).toPlainDomain()
            }
        }
    }


        override suspend fun insertDetail(detail: DetailEntity) {
            withContext(Dispatchers.IO) { detailDao.insertDetail(detail) }
        }

    override suspend fun deleteDetail(id: String) {
        withContext(Dispatchers.IO) {detailDao.deleteDetail(id)}
    }
}
