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

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductApiDataSource,
    private val productDao: ProductDao,

) : ProductRepository {

    override suspend fun getProducts(token: String, status: String, siteId: String, query: String) =
        withContext(Dispatchers.IO) { productDataSource.getProducts(token, status, siteId, query)}

    override suspend fun insertProduct(product: ProductPlainEntity) {
        withContext(Dispatchers.IO){productDao.insertProduct(product)}
    }

    override suspend fun deleteProduct(id :String) {
        withContext(Dispatchers.IO){
            productDao.deleteProduct(id)

        }
    }

    override fun getFavoriteProducts(): Flow<List<ProductPlainEntity>> = flow {
         emit(productDao.getFavoriteProducts())
    }

    override suspend fun getLocalId(): Flow<List<String>> = flow{
        emit(productDao.getLocalId())


    }
}